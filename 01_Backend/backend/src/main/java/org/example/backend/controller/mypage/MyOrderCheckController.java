package org.example.backend.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.mypage.OrderCheckDto;
import org.example.backend.model.dto.mypage.WishlistDto;
import org.example.backend.service.mypage.MyOrderCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : org.example.backend.controller.mypage
 * fileName : MyOrderCheckController
 * author : GGG
 * date : 2024-05-02
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02         GGG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyOrderCheckController {

    @Autowired
    MyOrderCheckService myOrderCheckService;

    @GetMapping("/ordercheck/{userId}")
    public ResponseEntity<Object> findAll(
            @PathVariable String userId
    )     {
        try{
//            전체 조회 서비스 함수 실행
            List<OrderCheckDto> getOrderCheck
                    = myOrderCheckService.getOrderCheck(userId);

            if(getOrderCheck.isEmpty() == false) {
//                조회성공
                return new ResponseEntity<>(getOrderCheck, HttpStatus.OK);
            } else {
//                조회실패
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            log.debug("에러 : "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
