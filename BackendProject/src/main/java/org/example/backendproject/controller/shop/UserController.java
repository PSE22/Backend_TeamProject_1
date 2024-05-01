package org.example.backendproject.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.entity.User;
import org.example.backendproject.service.shop.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : UserController
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
@Slf4j
@RestController
@RequestMapping("/api/shop")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    // User 상세 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> findById(@PathVariable String userId) {
        try {
            Optional<User> optionalUser = userService.findById(userId);
            if (optionalUser.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
