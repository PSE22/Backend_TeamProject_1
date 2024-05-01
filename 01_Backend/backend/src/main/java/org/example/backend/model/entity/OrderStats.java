package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.backend.model.entity
 * fileName : OrderStats
 * author : kimtaewan
 * date : 2024-04-29
 * description : 주문통계
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-29         kimtaewan          최초 생성
 */
@Entity
@Table(name="TB_ORDER_STATS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class OrderStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statId;

    private String statDate;

    private Integer dailyOrderCount;

    private Integer dailySales;

    private Integer weeklyOrderCount;

    private Integer weeklySales;

    private Integer monthlyOrderCount;

    private Integer monthlySales;

    private Integer yearlyOrderCount;

    private Integer yearlySales;

    public void addDailyStats(int orderCount, int sales) {
        this.dailyOrderCount = orderCount;
        this.dailySales = sales;
    }

    public void addWeeklyStats(int orderCount, int sales) {
        this.weeklyOrderCount = orderCount;
        this.weeklySales = sales;
    }

    public void addMonthlyStats(int orderCount, int sales) {
        this.monthlyOrderCount = orderCount;
        this.monthlySales = sales;
    }

    public void addYearlyStats(int orderCount, int sales) {
        this.yearlyOrderCount = orderCount;
        this.yearlySales = sales;
    }
}
