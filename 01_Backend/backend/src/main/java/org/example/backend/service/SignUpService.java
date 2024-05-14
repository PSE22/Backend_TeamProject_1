package org.example.backend.service;

import jakarta.transaction.Transactional;
import org.example.backend.model.entity.User;
import org.example.backend.model.entity.admin.AdminCoupon;
import org.example.backend.repository.UserRepository;
import org.example.backend.service.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    public void signUp(User user) {
        User user2 = userRepository.save(user);
        userRepository.save(user2);
    }

    //    상세조회
    public Optional<User> findById(String userId) {
        //    JPA 상세조회 함수 실행
        Optional<User> optionalUser
                = userRepository.findById(userId);
        return optionalUser;
    }
}