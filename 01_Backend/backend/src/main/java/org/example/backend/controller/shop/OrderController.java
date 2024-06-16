package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.shop.ICartDto;
import org.example.backend.model.dto.shop.IPointDto;
import org.example.backend.model.dto.shop.IUserCouponDto;
import org.example.backend.model.dto.shop.OrderDto;
import org.example.backend.model.entity.*;
import org.example.backend.service.shop.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // 주문 저장 함수
    @PostMapping("/order")
    public ResponseEntity<Object> create(@RequestBody OrderDto orderDto) {
        try {
            Order order2 = orderService.insert(orderDto);
            return new ResponseEntity<>(order2, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    // 새로 입력한 배송지 저장
    @PostMapping("/order/ship-address")
    public ResponseEntity<Object> createShipAddress(@RequestBody ShipAddress shipAddress) {
        try {
            ShipAddress shipAddress2 = orderService.saveShipAddress(shipAddress);
            return new ResponseEntity<>(shipAddress2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원의 쿠폰 정보 전체 조회
    @GetMapping("/order/user-coupon/{userId}")
    public ResponseEntity<Object> findByUserCouponUserId(@PathVariable String userId) {
        try {
            List<IUserCouponDto> listUserCoupon = orderService.findAllByUserCoupon(userId);
            if (listUserCoupon.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(listUserCoupon, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 쿠폰함 STATUS 컬럼을 'N' 으로 수정
    @PutMapping("/order/user-coupon/{cpId}/{userId}")
    public ResponseEntity<Object> updateCouponStatus(@PathVariable Long cpId,
                                                     @PathVariable String userId) {
        try {
            orderService.updateCouponStatus(cpId, userId);
            return new ResponseEntity<>("사용한 쿠폰 상태 'N' 으로 변경", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원의 포인트 잔액 상세 조회
    @GetMapping("/order/point/{userId}")
    public ResponseEntity<Object> findByResultPoint(@PathVariable String userId) {
        try {
            Optional<IPointDto> optionalPoint = orderService.findByResultPoint(userId);
            if (optionalPoint.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(optionalPoint.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원이 사용한 적립금 저장 (적립 테이블)
    @PostMapping("/order/point")
    public ResponseEntity<Object> createUsePoint(@RequestBody UsePoint usePoint) {
        try {
            UsePoint usePoint2 = orderService.savePoint(usePoint);
            log.debug("로그 : ", usePoint2);
            return new ResponseEntity<>(usePoint2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // 회원의 장바구니 정보 전체 조회
    @GetMapping("/order/cart/{userId}")
    public ResponseEntity<Object> findByCartUserId(@PathVariable String userId) {
        try {
            List<ICartDto> list = orderService.findByCartUserId(userId);
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 장바구니 삭제
    @DeleteMapping("/order/deletion/cart/{cartId}")
    public ResponseEntity<Object> delete(@PathVariable Long cartId) {
        try {
            // DB 삭제 서비스 함수 실행
            boolean success = orderService.removeByCartId(cartId);
            if (success == true) {
                // 삭제 성공
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실패 (삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
