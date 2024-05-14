package org.example.backend.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.admin.DailyOrderStatsDto;
import org.example.backend.model.dto.admin.MonthlyOrderStatsDto;
import org.example.backend.model.dto.admin.YearlyOrderStatsDto;
import org.example.backend.service.admin.OrderStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RestController
@RequestMapping("/api/admin")
public class OrderStatsController {

    @Autowired
    OrderStatsService orderStatsService;

    @GetMapping("/dailyOrderStats")
    public ResponseEntity<?> getDailyOrderStats(@RequestParam String odStatDate) {
        try {
            List<DailyOrderStatsDto> orderStatsList = orderStatsService.getDailyOrderStats(odStatDate);
            return ResponseEntity.ok(orderStatsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문통계 데이터가 존재하지 않습니다.");
        }
    }

    @GetMapping("/monthlyOrderStats")
    public ResponseEntity<?> getMonthlyOrderStats(@RequestParam String odStatDate) {
        try {
            List<MonthlyOrderStatsDto> orderStatsList = orderStatsService.getMonthlyOrderStats(odStatDate);
            return ResponseEntity.ok(orderStatsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문통계 데이터가 존재하지 않습니다.");
        }
    }

    @GetMapping("/yearlyOrderStats")
    public ResponseEntity<?> getYearlyOrderStats() {
        try {
            List<YearlyOrderStatsDto> orderStatsList = orderStatsService.getYearlyOrderStats();
            return ResponseEntity.ok(orderStatsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문통계 데이터가 존재하지 않습니다.");
        }
    }
}