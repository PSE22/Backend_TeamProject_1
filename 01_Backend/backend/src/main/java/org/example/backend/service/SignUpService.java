package org.example.backend.service;

import jakarta.transaction.Transactional;
import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<User> findById(String userId) {
        Optional<User> optionalUser
                = userRepository.findById(userId);
        return optionalUser;
    }
}