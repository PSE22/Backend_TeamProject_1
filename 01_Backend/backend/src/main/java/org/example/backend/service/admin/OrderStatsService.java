package org.example.backend.service.admin;

import org.example.backend.model.dto.admin.DailyOrderStatsDto;
import org.example.backend.model.dto.admin.MonthlyOrderStatsDto;
import org.example.backend.model.dto.admin.YearlyOrderStatsDto;
import org.example.backend.model.entity.admin.OrderStats;
import org.example.backend.repository.admin.OrderStatsRepository;
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
        DailyOrderStatsDto dailyStats = orderStatsRepository.findDailyStats();
        MonthlyOrderStatsDto monthlyStats = orderStatsRepository.findMonthlyStats();
        YearlyOrderStatsDto yearlyStats = orderStatsRepository.findYearlyStats();

        // 모든 통계 정보가 유효한지 확인하고 합치기
        if (dailyStats != null && monthlyStats != null && yearlyStats != null) {
            // 통계 정보를 하나의 통합된 객체로 만들기
            OrderStats combinedStats = new OrderStats();
            combinedStats.setOdStatDate(dailyStats.getOdStatDate()); // OD_STAT_DATE는 하나로 통일될 것이므로 아무거나 선택하여 설정
            combinedStats.setDailyOrderCnt(dailyStats.getDailyOrderCnt());
            combinedStats.setDailySales(dailyStats.getDailySales());
            combinedStats.setMonthlyOrderCnt(monthlyStats.getMonthlyOrderCnt());
            combinedStats.setMonthlySales(monthlyStats.getMonthlySales());
            combinedStats.setYearlyOrderCnt(yearlyStats.getYearlyOrderCnt());
            combinedStats.setYearlySales(yearlyStats.getYearlySales());

            // 통합된 통계 정보 삽입
            orderStatsRepository.save(combinedStats);
        }
    }

    public List<DailyOrderStatsDto> getDailyOrderStats(String odStatDate) {
        return orderStatsRepository.findDailyOrderStats(odStatDate);
    }

    public List<MonthlyOrderStatsDto> getMonthlyOrderStats(String odStatDate) {
        return orderStatsRepository.findMonthlyOrderStats(odStatDate);
    }

    public List<YearlyOrderStatsDto> getYearlyOrderStats() {
        return orderStatsRepository.findYearlyOrderStats();
    }
}

// 주문이 생성될 때 통계 업데이트 호출하는 함수 -> 주문 서비스에 포함해야함
//        orderStatsService.updateStatsOnOrderCreation();