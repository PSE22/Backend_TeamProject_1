package org.example.backend.service.admin;

import org.example.backend.model.entity.OrderStats;
import org.example.backend.repository.OrderStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.backend.service.admin
 * fileName : OrderStatsService
 * author : kimtaewan
 * date : 2024-05-01
 * description : 주문통계
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         kimtaewan          최초 생성
 */
@Service
public class OrderStatsService {

    @Autowired
    private OrderStatsRepository orderStatsRepository;

    public void updateOrderStats() {
        OrderStats dailyStats = orderStatsRepository.findDailyStats();
        if (dailyStats != null) {
            orderStatsRepository.save(dailyStats);
        }

        OrderStats monthlyStats = orderStatsRepository.findMonthlyStats();
        if (monthlyStats != null) {
            orderStatsRepository.save(monthlyStats);
        }

        OrderStats yearlyStats = orderStatsRepository.findYearlyStats();
        if (yearlyStats != null) {
            orderStatsRepository.save(yearlyStats);
        }
    }

    public void updateStatsOnOrderCreation() {
        updateOrderStats();
    }

    public List<OrderStats> getOrderStats() {
        return orderStatsRepository.findAll();
    }
}

// 주문이 생성될 때 통계 업데이트 호출하는 함수 -> 주문 서비스에 포함해야함
//        orderStatsService.updateStatsOnOrderCreation();