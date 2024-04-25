package org.example.backend.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.User;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class UpdateProfileController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/updateProfile")
    public ResponseEntity<Object> showUpdateProfileForm(@RequestParam("userPw") String userPw, @RequestParam("token") String token) {
        try {
            String userId = (String) httpSession.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            }

            boolean isPasswordCorrect = userService.isPasswordCorrect(userPw);
            if (!isPasswordCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
            }

            User currentUser = userService.getCurrentUser(token);
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
            }

            return ResponseEntity.ok(currentUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
    }


    @PostMapping("/updateProfile")
    public ResponseEntity<Object> updateProfile(@ModelAttribute("user") User user, String userPw) {
        try {
            String userId = (String) httpSession.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            }

            boolean isPasswordCorrect = userService.isPasswordCorrect(userPw);
            if (!isPasswordCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
            }

            userService.updateUser(user);
            return ResponseEntity.ok("회원정보가 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원정보 수정 중 오류가 발생했습니다.");
        }
    }
}
