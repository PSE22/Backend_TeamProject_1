package org.example.backend.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.User;
import org.example.backend.service.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * packageName : org.example.backend.controller
 * fileName : EditProfileController
 * author : kimtaewan
 * date : 2024-04-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/mypage/{userId}")
@RequiredArgsConstructor
public class EditProfileController {

    @Autowired
    EditProfileService userService;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/editProfile")
    public ResponseEntity<Object> showUpdateProfileForm(@RequestParam("userPw") String userPw) {
        try {
            String userId = (String) httpSession.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            }

            boolean isPasswordCorrect = userService.isPasswordCorrect(userPw);
            if (!isPasswordCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
            }

            User currentUser = userService.getCurrentUser();
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
            }

            return ResponseEntity.ok(currentUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
    }


    @PostMapping("/editProfile")
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

    @DeleteMapping("/editProfile")
    public ResponseEntity<Object> withdrawUser(@RequestParam("userPw") String userPw) {
        try {
            String userId = (String) httpSession.getAttribute("userId");
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
            }

            userService.withdrawUser(userPw);
            return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴 중 오류가 발생했습니다.");
        }
    }
}
