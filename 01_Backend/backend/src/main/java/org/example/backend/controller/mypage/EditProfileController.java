package org.example.backend.controller.mypage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.User;
import org.example.backend.service.mypage.EditProfileService;
import org.example.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
    EditProfileService editProfileService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/editProfile")
    public ResponseEntity<Object> showUpdateProfileForm(@PathVariable String userId) {
        try {
        String currentUser = editProfileService.getCurrentUser(userId);
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
            }

            return ResponseEntity.ok(currentUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
    }

    @PostMapping("/editProfile")
    public ResponseEntity<Object> updateProfile(@ModelAttribute("user") User user,
                                                @PathVariable String userId,
                                                @RequestParam(value = "userPw", required = true) String userPw,
                                                @RequestParam(value = "newPw", required = false) String newPw) {
        try {
            boolean isPasswordCorrect = editProfileService.isPasswordCorrect(userId, userPw);
            if (!isPasswordCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("현재 비밀번호가 일치하지 않습니다.");
            }

            if (newPw != null && !newPw.isEmpty()) {
                String encryptedNewPassword = passwordEncoder.encode(newPw);
                user.setUserPw(encryptedNewPassword);
            }

            editProfileService.updateUser(user);

            return ResponseEntity.ok("회원정보가 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원정보 수정 중 오류가 발생했습니다.");
        }
    }

    @DeleteMapping("/editProfile")
    public ResponseEntity<Object> withdrawUser(
            @PathVariable String userId,
            @RequestParam(value = "userPw", required = true) String userPw) {
        try {
            editProfileService.withdrawUser(userId, userPw);
            return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴 중 오류가 발생했습니다.");
        }
    }
}
