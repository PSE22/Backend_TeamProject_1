package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.OrderCancel;
import org.example.backend.model.entity.OrderDetail;
import org.example.backend.model.entity.Refund;
import org.example.backend.service.OrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : org.example.backend.controller
 * fileName : OrderManageController
 * author : kimtaewan
 * date : 2024-04-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/Admin")
@RequiredArgsConstructor
public class OrderManageController {

    @Autowired
    OrderManageService orderManageService;

    @GetMapping("/orderList")
    public ResponseEntity<Object> showOrders() {
        try {
            List<Order> orders = orderManageService.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 목록을 가져오는 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderDetail(@PathVariable Long orderId) {
        try {
            Order order = orderManageService.getOrderById(orderId);
            List<OrderDetail> orderDetails = orderManageService.getOrderDetails(orderId.intValue());
            if (order != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("order", order);
                response.put("orderDetails", orderDetails);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 정보를 가져오는 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/{orderId}/cancelInfo")
    public ResponseEntity<Object> getOrderCancelInfo(@PathVariable Long orderId) {
        try {
            OrderCancel orderCancel = orderManageService.getOrderCancelInfo(orderId);
            if (orderCancel != null) {
                return ResponseEntity.ok(orderCancel);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문 취소 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 취소 정보를 가져오는 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/{orderId}/refundInfo")
    public ResponseEntity<Object> getRefundInfo(@PathVariable Long orderId) {
        try {
            Refund refund = orderManageService.getRefundInfo(orderId);
            if (refund != null) {
                return ResponseEntity.ok(refund);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("환불 정보를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("환불 정보를 가져오는 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Object> updateOrderCode(@PathVariable Long orderId, @RequestParam String newOrderCode) {
        try {
            Order updatedOrderCode = orderManageService.updateOrderCode(orderId, newOrderCode);
            if (updatedOrderCode != null) {
                return ResponseEntity.ok("주문 상태가 성공적으로 변경되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 상태를 업데이트하는 중 오류가 발생했습니다.");
        }
    }
}