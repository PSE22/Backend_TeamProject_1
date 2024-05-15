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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity<List<OrderCheckDto>> getOrdersByDateRange(
            @PathVariable String userId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            List<OrderCheckDto> orders = myOrderCheckService.findOrdersByDateRange(userId, startDate, endDate);
            if (orders.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(orders);
            }
        } catch (Exception e) {
            log.error(" 오류 " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
