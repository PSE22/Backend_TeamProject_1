package org.example.backendproject.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.entity.ShipAddress;
import org.example.backendproject.model.entity.User;
import org.example.backendproject.model.entity.UserCoupon;
import org.example.backendproject.service.shop.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : OrderController
 * author : SAMSUNG
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         SAMSUNG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    // 주문자(User) 정보 상세 조회
    @GetMapping("/order/{userId}")
    public ResponseEntity<Object> findById(@PathVariable String userId) {
        try {
            Optional<User> optionalUser = orderService.findById(userId);
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

    // 배송지(ShipAddress) 정보 상세 조회
    @GetMapping("/order/ship-address/{userId}")
    public ResponseEntity<Object> findByUserId(@PathVariable String userId) {
        try {
            Optional<ShipAddress> optionalShipAddress = orderService.findByUserId(userId);
            if (optionalShipAddress.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(optionalShipAddress.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원의 쿠폰 정보 전체 조회
    @GetMapping("/order/user-coupon/{userId}")
    public ResponseEntity<Object> findByUserCouponUserId(@PathVariable String userId) {
        try {
            List<UserCoupon> listUserCoupon = orderService.findByUserCouponUserId(userId);
            if (listUserCoupon.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                log.debug("왜 에러? 11 ");
                return new ResponseEntity<>(listUserCoupon, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.debug("왜 에러? 22 " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
