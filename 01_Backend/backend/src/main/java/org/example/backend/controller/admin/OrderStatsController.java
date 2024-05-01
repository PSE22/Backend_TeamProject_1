package org.example.backend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.OrderStats;
import org.example.backend.service.admin.OrderStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName : org.example.backend.controller.admin
 * fileName : OrderStatsController
 * author : kimtaewan
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         kimtaewan          최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/orderstats")
public class OrderStatsController {

    @Autowired
    OrderStatsService orderStatsService;

    @GetMapping
    public ResponseEntity<?> getOrderStats() {
        List<OrderStats> orderStatsList = orderStatsService.getOrderStats();
        if (orderStatsList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문통계 데이터가 존재하지 않습니다.");
        }
        return ResponseEntity.ok(orderStatsList);
    }
}