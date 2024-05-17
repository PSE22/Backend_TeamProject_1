package org.example.backend.controller.mypage;

import org.example.backend.service.SignUpService;
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

import java.util.Optional;

/**
 * packageName : org.example.backend.controller
 * fileName : EditProfileController
 * author :
 * date : 2024-04-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
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

    @Autowired
    SignUpService signUpService;

//    TODO: 상세조회
    @GetMapping("/editProfile/{userId}")
    public ResponseEntity<Object> findById(
            @PathVariable String userId
    ) {
        try {
            Optional<User> optionalUser = signUpService.findById(userId);
            if (optionalUser.isEmpty() == true) {
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalUser.get()
                        , HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 수정함수
    @PutMapping("/editProfile/{userId}")
    public ResponseEntity<Object> update(
            @PathVariable String userId,
            @RequestBody User user
    ) {
        try {
            if (user.getUserPw().equals("")) {
                //유저를 상세조회해서 패스워드를 가져와서 유저패스워드에 넣기
                Optional<User> optionalUser = signUpService.findById(userId);
                user.setUserPw(optionalUser.get().getUserPw());
            } else {
                user.setUserPw(passwordEncoder.encode(user.getUserPw()));
            }
            User user2 = editProfileService.updateUser(user);

            return new ResponseEntity<>(user2, HttpStatus.OK);
        } catch (Exception e) {
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    TODO: 탈퇴 함수
    @DeleteMapping("/editProfile/deletion/{userId}/{userPw}")
    public boolean withdrawUser(@PathVariable String userId, @PathVariable String userPw) {
        try {
            // 사용자 ID로 사용자 정보 조회
            Optional<User> optionalUser = signUpService.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                // 입력한 비밀번호와 사용자의 저장된 비밀번호를 비교하여 일치하는지 확인
                if (passwordEncoder.matches(userPw, user.getUserPw())) {
                    // 일치할 경우 회원 탈퇴 수행
                    editProfileService.withdrawUser(userId, userPw);
                    return true;
                } else {
                    // 비밀번호가 일치하지 않을 경우 예외 발생
                    throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
                }
            } else {
                // 사용자가 존재하지 않을 경우 예외 발생
                throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
            }
        } catch (Exception e) {
            // 예외 처리
            log.error("탈퇴 중 오류 발생: " + e.getMessage());
            return false;
        }
    }


}
