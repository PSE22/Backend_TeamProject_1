package org.example.backend.service;

import jakarta.transaction.Transactional;
import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.service.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backend.service
 * fileName : SignUpService
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
@Service
@Transactional
public class SignUpService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    public void signUp(SignUpRequest signUpRequest) {
        User user = signUpRequest.toUser(passwordEncoder);
        userRepository.save(user);
    }
}
