package org.example.backend.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.mypage.IMyOrderDetailDto;
import org.example.backend.model.dto.mypage.OrderCancelDto;
import org.example.backend.model.dto.mypage.OrderRefundDto;
import org.example.backend.model.entity.OrderDetail;
import org.example.backend.service.CmCodeService;
import org.example.backend.service.mypage.MyOrderCancelService;
import org.example.backend.service.mypage.MyOrderDetailService;
import org.example.backend.service.mypage.MyOrderRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.controller.admin
 * fileName : AdminOrderDetailController
 * author : kimtaewan
 * date : 2024-05-18
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-18         kimtaewan          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminOrderDetailController {
    @Autowired
    MyOrderDetailService myOrderDetailService;

    @Autowired
    CmCodeService cmCodeService;

    @Autowired
    MyOrderCancelService myOrderCancelService;

    @Autowired
    MyOrderRefundService myOrderRefundService;

    //    TODO: 배송지
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/order-detail/{orderId}")
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
    @GetMapping("/order-list/{orderId}")
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
    @GetMapping("/order-price/{orderId}")
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
    @GetMapping("/order-code/{orderId}")
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

    //    TODO: 주문 상세 조회
//    조회(select) -> get 방식 -> GetMapping
    @GetMapping("/order-orderId/{orderId}")
    public ResponseEntity<Object> findById(
            @PathVariable Long orderId
    ) {
        try {
//            상세조회 서비스 실행
            List<OrderDetail> orderDetailList
                    = myOrderCancelService.findByOrderIdAndOpId(orderId);
            if (orderDetailList.isEmpty() == true) {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                조회 성공
                return new ResponseEntity<>(orderDetailList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.debug("에러:"+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 주문 취소 저장 함수
    @PostMapping("/order-cancel")
    public ResponseEntity<Object> create(@RequestBody OrderCancelDto orderCancelDto) {
        try {
            log.debug("데이터 : " + orderCancelDto);
            myOrderCancelService.insert(orderCancelDto);    // 취소 테이블 저장
            myOrderCancelService.removeById(orderCancelDto.getOrderId());   // 주문 삭제, 코드 변경
            return ResponseEntity.ok().build();
        } catch (DataAccessException e) {
            log.debug("Database error while processing order cancellation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        } catch (Exception e) {
            log.debug("An error occurred while processing order cancellation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    //    TODO: 환불신청 저장
    @PostMapping("/order-refund")
    public ResponseEntity<?> createRefund(@RequestBody OrderRefundDto orderRefundDto) {
        try {
            log.debug("데이터 : " + orderRefundDto);
            myOrderRefundService.insertRefund(orderRefundDto);
            myOrderCancelService.removeById(orderRefundDto.getOrderId());

            return ResponseEntity.ok().build();
        } catch (DataAccessException e) {
            log.debug("Database error while processing order cancellation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error: " + e.getMessage());
        } catch (Exception e) {
            log.debug("An error occurred while processing order cancellation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
