package org.example.backend.controller;

import jakarta.servlet.http.HttpSession;
import org.example.backend.model.entity.User;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/updateProfile")
    public String showUpdateProfileForm(Model model, @RequestParam("userPw") String userPw, @RequestParam("token") String token) {
        String userId = (String) httpSession.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        boolean isPasswordCorrect = userService.isPasswordCorrect(userPw);
        if (!isPasswordCorrect) {
            return "redirect:/login";
        }

        User currentUser = userService.getCurrentUser(token);
        if (currentUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", currentUser);
        return "update_profile";
    }


    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@ModelAttribute("user") User user, String userPw) {
        String userId = (String) httpSession.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        boolean isPasswordCorrect = userService.isPasswordCorrect(userPw);
        if (!isPasswordCorrect) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
        }

        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("회원정보가 수정되었습니다.");
    }
}
