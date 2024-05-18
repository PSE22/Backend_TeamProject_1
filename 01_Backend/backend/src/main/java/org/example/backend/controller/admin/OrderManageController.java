package org.example.backend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.CmCode;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.OrderCancel;
import org.example.backend.model.entity.OrderDetail;
import org.example.backend.model.entity.Refund;
import org.example.backend.service.CmCodeService;
import org.example.backend.service.OrderCancelService;
import org.example.backend.service.RefundService;
import org.example.backend.service.admin.OrderManageService;
import org.example.backend.service.shop.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class OrderManageController {

    @Autowired
    OrderManageService orderManageService;

    @Autowired
    CmCodeService cmCodeService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderCancelService orderCancelService;

    @Autowired
    RefundService refundService;

    @GetMapping("/orders")
    public ResponseEntity<Object> findAll(
            @RequestParam(required = false) Long orderId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            Page<Order> orderListPage = orderManageService.findAllById(orderId, pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("orders", orderListPage.getContent());
            response.put("currentPage", orderListPage.getNumber());
            response.put("totalItems", orderListPage.getTotalElements());
            response.put("totalPages", orderListPage.getTotalPages());

            if (!orderListPage.isEmpty()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.noContent().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Object> getOrderDetail(@PathVariable Long orderId) {
        try {
            Optional<Order> optionalOrder = orderManageService.findById(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                Map<String, Object> response = new HashMap<>();
                response.put("order", order);

                List<OrderDetail> orderDetails = orderManageService.getOrderDetails(orderId)
                        .orElse(null);
                response.put("orderDetails", orderDetails);

                OrderCancel orderCancel = orderManageService.getOrderCancelInfo(orderId)
                        .orElse(null);
                response.put("orderCancel", orderCancel);

                Refund refund = orderManageService.getRefundInfo(orderId)
                        .orElse(null);
                response.put("refund", refund);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/orders/cmcd")
    public ResponseEntity<Object> getCmCode() {
        try {
            List<CmCode> cmCode = cmCodeService.findByUpCmCd();
            return ResponseEntity.ok(cmCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/orders/{orderId}/{cmCode}")
    public ResponseEntity<Object> codeChange(@PathVariable String cmCode, @RequestParam Long orderId, @RequestParam Long opId) {
        try {
            if (cmCode.startsWith("OD01")) {
                Optional<Order> order = orderService.findByOrderId(orderId); // orderId를 사용하여 주문 검색
                if (order.isPresent()) {
                    orderService.updateOrderCode(order.orElse(null));
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문정보를 찾을 수 없습니다.");
                }
            }else if (cmCode.startsWith("OD02")) {
                Optional<OrderCancel> orderCancel = orderCancelService.findByOrderId(orderId); // orderId를 사용하여 주문 검색
                if (orderCancel.isPresent()) {
                    orderCancelService.updateOrderCancel(orderCancel.orElse(null));
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문정보를 찾을 수 없습니다.");
                }
            } else if (cmCode.startsWith("OD03")) {
                Optional<Refund> refund = refundService.findByOpId(opId); // orderId를 사용하여 주문 검색
                if (refund.isPresent()) {
                    refundService.updateRefund(refund.orElse(null));
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문정보를 찾을 수 없습니다.");
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("주문정보를 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<Object> updateCodes(
            @PathVariable Long orderId,
            @RequestParam(required = false) String newOrderCode,
            @RequestParam(required = false) String newOcCode,
            @RequestParam(required = false) String newRefundCode
    ) {
        try {
            if (newOrderCode != null) {
                Order updatedOrder = orderManageService.updateOrderCode(orderId, newOrderCode);
                if (updatedOrder != null) {
                    return ResponseEntity.ok("Order code updated successfully.");
                } else {
                    return ResponseEntity.notFound().build();
                }
            }

            if (newOcCode != null) {
                OrderCancel updatedCancelCode = orderManageService.updateOrderCancelCode(orderId, newOcCode);
                if (updatedCancelCode != null) {
                    return ResponseEntity.ok("Cancel code updated successfully.");
                } else {
                    return ResponseEntity.notFound().build();
                }
            }

            if (newRefundCode != null) {
                Refund updatedRefundCode = orderManageService.updateRefundCode(orderId, newRefundCode);
                if (updatedRefundCode != null) {
                    return ResponseEntity.ok("Refund code updated successfully.");
                } else {
                    return ResponseEntity.notFound().build();
                }
            }

            return ResponseEntity.badRequest().body("No code updates requested.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}