package org.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.User;
import org.example.backend.service.EditProfileService;
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
    public ResponseEntity<Object> showUpdateProfileForm() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean hasPermission = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("AT02"));
            if (!hasPermission) {
                throw new AccessDeniedException("해당 권한이 없습니다.");
            }

            User currentUser = editProfileService.getCurrentUser();
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
            }

            return ResponseEntity.ok(currentUser);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
        }
    }

    @PostMapping("/editProfile")
    public ResponseEntity<Object> updateProfile(@ModelAttribute("user") User user,
                                                @RequestParam(value = "userPw", required = true) String userPw,
                                                @RequestParam(value = "newPw", required = false) String newPw) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean hasPermission = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("AT02"));
            if (!hasPermission) {
                throw new AccessDeniedException("해당 권한이 없습니다.");
            }

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
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원정보 수정 중 오류가 발생했습니다.");
        }
    }

    @DeleteMapping("/editProfile")
    public ResponseEntity<Object> withdrawUser(@RequestParam("userPw") String userPw) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean hasPermission = authorities.stream()
                    .anyMatch(auth -> auth.getAuthority().equals("AT02"));
            if (!hasPermission) {
                throw new AccessDeniedException("해당 권한이 없습니다.");
            }

            boolean isPasswordCorrect = editProfileService.isPasswordCorrect(userId, userPw);
            if (!isPasswordCorrect) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
            }

            editProfileService.withdrawUser(userPw);

            return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴 중 오류가 발생했습니다.");
        }
    }
}
