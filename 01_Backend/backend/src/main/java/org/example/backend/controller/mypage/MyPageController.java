package org.example.backend.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.mypage.IOrderCodeDto;
import org.example.backend.model.entity.Order;
import org.example.backend.service.mypage.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName : org.example.backend.controller.mypage
 * fileName : MyPageController
 * author : gumiji
 * date : 5/8/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/8/24         gumiji          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {
    @Autowired
    MyPageService myPageService;

    //    쿠폰 카운트
    @GetMapping("/couponcnt/{userId}")
    public ResponseEntity<Object> couponCount(
            @PathVariable String userId
    ) {
        try {
            Integer couponCount
                    = myPageService.couponCount(userId);
            return new ResponseEntity<>(couponCount, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    주문카운트
    @GetMapping("/ordercnt/{userId}")
    public ResponseEntity<Object> orderCount(
            @PathVariable String userId
    ) {
        try {
//            전체 조회 서비스 함수 실행
            Integer orderCount
                    = myPageService.orderCount(userId);
            return new ResponseEntity<>(orderCount, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    리뷰카운트
    @GetMapping("/reviewcnt/{userId}")
    public ResponseEntity<Object> reviewCount(
            @PathVariable String userId
    ) {
        try {
//            전체 조회 서비스 함수 실행
            Integer reviewCount
                    = myPageService.reviewCount(userId);
            return new ResponseEntity<>(reviewCount, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    문의 카운트
    @GetMapping("/inquirycnt/{userId}")
    public ResponseEntity<Object> inquiryCount(
            @PathVariable String userId
    ) {
        try {
//            전체 조회 서비스 함수 실행
            Integer inquiryCount
                    = myPageService.inquiryCount(userId);
            return new ResponseEntity<>(inquiryCount, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    유저 이름
    @GetMapping("/username/{userId}")
    public ResponseEntity<Object> findByUserName(
            @PathVariable String userId
    ) {
        try {
//            전체 조회 서비스 함수 실행
            String findByUserName
                    = myPageService.findByUserName(userId);
            return new ResponseEntity<>(findByUserName, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    주문정보 카운트
    @GetMapping("/ordercode/{userId}")
    public ResponseEntity<Object> orderCodeCount(
            @PathVariable String userId
    ) {
        try {
//            전체 조회 서비스 함수 실행
            List<IOrderCodeDto> orderCodeCount
                    = myPageService.orderCodeCount(userId);
            return new ResponseEntity<>(orderCodeCount, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
