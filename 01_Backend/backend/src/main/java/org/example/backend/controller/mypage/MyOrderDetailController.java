package org.example.backend.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.mypage.IMyOrderDetailDto;
import org.example.backend.service.CmCodeService;
import org.example.backend.service.mypage.MyOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.controller.mypage
 * fileName : MyOrderDetailController
 * author : gumiji
 * date : 5/10/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/10/24         gumiji          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyOrderDetailController {

    @Autowired
    MyOrderDetailService myOrderDetailService;

    @Autowired
    CmCodeService cmCodeService;

    //    TODO: 배송지
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/orderdetail/{orderId}")
    public ResponseEntity<Object> findByOrderId(
            @PathVariable Long orderId
    ) {
        try {
//            상세조회 서비스 실행
            Optional<IMyOrderDetailDto> optionalIMyOrderDetail
                    = myOrderDetailService.findByOrderId(orderId);
            if(optionalIMyOrderDetail.isEmpty() == true) {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                조회 성공
                return new ResponseEntity<>(optionalIMyOrderDetail.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 주문 상품리스트
    @GetMapping("/orderlist/{orderId}")
    public ResponseEntity<Object> findByOrderList(
            @PathVariable Long orderId
    ) {
        try {
//            상세조회 서비스 실행
            List<IMyOrderDetailDto> findByOrderList
                    = myOrderDetailService.findByOrderList(orderId);

            if(findByOrderList.isEmpty() == true) {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                조회 성공
                return new ResponseEntity<>(findByOrderList, HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 주문 결제금액
    @GetMapping("/orderprice/{orderId}")
    public ResponseEntity<Object> findByOrderPrice(
            @PathVariable Long orderId
    ) {
        try {
//            상세조회 서비스 실행
            Optional<IMyOrderDetailDto> optionalIMyOrderDetail
                    = myOrderDetailService.findByOrderPrice(orderId);

            if(optionalIMyOrderDetail.isEmpty() == true) {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                조회 성공
                return new ResponseEntity<>(optionalIMyOrderDetail.get(), HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    주문 코드
    @GetMapping("/ordercode/{orderId}")
    public ResponseEntity<Object> findByCmCdName(
            @PathVariable Long orderId
    ) {
        try {
//            전체 조회 서비스 함수 실행
            String findByCmCdName
                    = cmCodeService.findByCmCdName(orderId);
            return new ResponseEntity<>(findByCmCdName, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}