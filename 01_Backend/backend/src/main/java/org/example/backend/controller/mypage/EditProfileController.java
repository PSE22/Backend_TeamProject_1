package org.example.backend.controller.mypage;

import org.example.backend.model.entity.admin.AdminCoupon;
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
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class EditProfileController {

    @Autowired
    EditProfileService editProfileService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @GetMapping("/editProfile/{userId}")
//    public ResponseEntity<Object> showUpdateProfileForm() {
//        try {
//        String currentUser = editProfileService.getCurrentUser(userId);
//            if (currentUser == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
//            }
//
//            return ResponseEntity.ok(currentUser);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다.");
//        }
//    }

//    @PutMapping("/editProfile/{userId}")
//    public ResponseEntity<Object> updateProfile(
//            @PathVariable String userId,
//            @RequestBody User user
//    ) {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String currentUserId = authentication.getName();
//
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            boolean hasPermission = authorities.stream()
//                    .anyMatch(auth -> auth.getAuthority().equals("AT02"));
//            if (!hasPermission) {
//                throw new AccessDeniedException("해당 권한이 없습니다.");
//            }
//
//            // 클라이언트에서 전달된 비밀번호 확인
//            boolean isPasswordCorrect = editProfileService.isPasswordCorrect(userId, user.getUserPw());
//            if (!isPasswordCorrect) {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("현재 비밀번호가 일치하지 않습니다.");
//            }
//
//            // 새로운 비밀번호 암호화
//            if (user.getUserPw() != null && !user.getUserPw().isEmpty()) {
//                String encryptedNewPassword = passwordEncoder.encode(user.getUserPw());
//                user.setUserPw(encryptedNewPassword);
//            }
//
//            // 사용자 정보 업데이트
//            editProfileService.updateUser(user);
//
//            return ResponseEntity.ok("회원정보가 수정되었습니다.");
//        } catch (AccessDeniedException e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원정보 수정 중 오류가 발생했습니다.");
//        }
//    }

    //    TODO: 수정함수
    @PutMapping("/editProfile/{userId}")
    public ResponseEntity<Object> update(
            @PathVariable String userId,
            @RequestBody User user
    ) {
        try {
            log.debug("확인 필요"+user);
            User user2 = editProfileService.updateUser(user);  // 수정

            return new ResponseEntity<>(user2, HttpStatus.OK);
        } catch (Exception e) {
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping("/editProfile/{userId}")
//    public ResponseEntity<Object> withdrawUser(@RequestParam("userPw") String userPw) {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String userId = authentication.getName();
//
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            boolean hasPermission = authorities.stream()
//                    .anyMatch(auth -> auth.getAuthority().equals("AT02"));
//            if (!hasPermission) {
//                throw new AccessDeniedException("해당 권한이 없습니다.");
//            }
//
//            boolean isPasswordCorrect = editProfileService.isPasswordCorrect(userId, userPw);
//            if (!isPasswordCorrect) {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
//            }
//
//            editProfileService.withdrawUser(userPw);
//
//            return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
//        } catch (AccessDeniedException e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴 중 오류가 발생했습니다.");
//        }
//    }

    //     TODO: 삭제 함수
    @DeleteMapping("/editProfile/deletion/{userId}")
    public ResponseEntity<Object> delete(
            @PathVariable String userId
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = editProfileService.withdrawUser(userId);

            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버(DB) 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
