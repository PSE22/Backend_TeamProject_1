package org.example.backendproject.service.shop;

import org.example.backendproject.model.entity.User;
import org.example.backendproject.repository.shop.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : UserService
 * author : SAMSUNG
 * date : 2024-04-30
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30         SAMSUNG          최초 생성
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    // User 상세 조회
    public Optional<User> findById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser;
    }
}
