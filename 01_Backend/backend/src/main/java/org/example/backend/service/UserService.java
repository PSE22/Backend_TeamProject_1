package org.example.backend.service;

import jakarta.servlet.http.HttpSession;
import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Service
public class UserService {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isPasswordCorrect(String userPw) {
        String userId = (String) httpSession.getAttribute("userId");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(userPw, user.getUserPw());
        }
        return false;
    }

    // 토큰을 사용하여 현재 사용자 정보를 가져오는 메서드
    public User getCurrentUser(String token) {
        if (!jwtUtils.validateJwtToken(token)) {
            log.error("유효하지 않은 토큰입니다.");
            return null;
        }

        String userId = jwtUtils.getUserIdFromJwtToken(token); // 토큰에서 사용자 아이디 가져오기
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
