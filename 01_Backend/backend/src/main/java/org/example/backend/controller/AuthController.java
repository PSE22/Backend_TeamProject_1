package org.example.backend.controller;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.User;
import org.example.backend.security.jwt.JwtUtils;
import org.example.backend.service.dto.LoginRequest;
import org.example.backend.service.dto.LoginResponse;
import org.example.backend.service.dto.SignUpRequest;
import org.example.backend.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * packageName : org.example.backend.controller
 * fileName : SignUpController
 * author : kimtaewan
 * date : 2024-04-18
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18         kimtaewan          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    SignUpService signUpService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody LoginRequest loginRequest
            ) {
        try {
            Authentication authentication
                    = authenticationManagerBuilder.getObject().authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getUserPw())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            String userCode = new ArrayList(authentication.getAuthorities()).get(0).toString();

            LoginResponse loginResponse = new LoginResponse(
                    jwt,
                    loginRequest.getUserId(),
                    userCode
            );
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ID 또는 비밀번호가 일치하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/signup/{userId}")
    public ResponseEntity<Object> reId(@RequestParam String userId) {
        try {
            if(signUpService.existsById(userId)) {
                return ResponseEntity.badRequest().body("이미 가입된 회원입니다.");
            } else {
                return ResponseEntity.ok("사용 가능한 ID 입니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            if(signUpService.existsById(signUpRequest.getUserId())) {
                return ResponseEntity.badRequest().body("이미 가입된 회원입니다.");
            }
                User user = new User(
                signUpRequest.getUserId(),
                passwordEncoder.encode(signUpRequest.getUserPw()),
                signUpRequest.getUserName(),
                signUpRequest.getUserBirth(),
                signUpRequest.getGender(),
                signUpRequest.getUserEmail(),
                signUpRequest.getUserPhone(),
                signUpRequest.getUserPromo(),
                signUpRequest.getUserCode()
                        );
                signUpService.signUp(user);
                return ResponseEntity.ok("회원가입이 완료되었습니다.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
