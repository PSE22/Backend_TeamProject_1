package org.example.backend.repository;

import org.example.backend.model.entity.OrderStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backend.repository
 * fileName : OrderStatsRepository
 * author : kimtaewan
 * date : 2024-04-29
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-29         kimtaewan          최초 생성
 */
@Repository
public interface OrderStatsRepository extends JpaRepository<OrderStats, Long> {

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') AS STAT_DATE, " +
            "COUNT(O.ORDER_ID) AS DAILY_ORDER_COUNT, " +
            "SUM(OD.ORDER_DETAIL_PRICE) AS DAILY_SALES " +
            "FROM TB_ORDER_STATS OS " +
            "LEFT JOIN TB_ORDER O ON OS.STAT_DATE = O.ORDER_DATE " +
            "LEFT JOIN TB_ORDER_DETAIL OD ON O.ORDER_ID = OD.ORDER_ID " +
            "WHERE STAT_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') " +
            "GROUP BY STAT_DATE", nativeQuery = true)
    OrderStats findDailyStats();

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'IYYY-IW') AS WEEK, " +
            "COUNT(O.ORDER_ID) AS WEEKLY_ORDER_COUNT, " +
            "SUM(OD.ORDER_DETAIL_PRICE) AS WEEKLY_SALES " +
            "FROM TB_ORDER_STATS OS " +
            "LEFT JOIN TB_ORDER O ON OS.STAT_DATE = O.ORDER_DATE " +
            "LEFT JOIN TB_ORDER_DETAIL OD ON O.ORDER_ID = OD.ORDER_ID " +
            "WHERE TO_CHAR(STAT_DATE, 'IYYY-IW') = TO_CHAR(SYSDATE, 'IYYY-IW') " +
            "GROUP BY WEEK", nativeQuery = true)
    List<OrderStats> findWeeklyStats();

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM') AS MONTH, " +
            "COUNT(O.ORDER_ID) AS MONTHLY_ORDER_COUNT, " +
            "SUM(OD.ORDER_DETAIL_PRICE) AS MONTHLY_SALES " +
            "FROM TB_ORDER_STATS OS " +
            "LEFT JOIN TB_ORDER O ON OS.STAT_DATE = O.ORDER_DATE " +
            "LEFT JOIN TB_ORDER_DETAIL OD ON O.ORDER_ID = OD.ORDER_ID " +
            "WHERE TO_CHAR(STAT_DATE, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM') " +
            "GROUP BY MONTH", nativeQuery = true)
    OrderStats findMonthlyStats();

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY') AS YEAR, " +
            "COUNT(O.ORDER_ID) AS YEARLY_ORDER_COUNT, " +
            "SUM(OD.ORDER_DETAIL_PRICE) AS YEARLY_SALES " +
            "FROM TB_ORDER_STATS OS " +
            "LEFT JOIN TB_ORDER O ON OS.STAT_DATE = O.ORDER_DATE " +
            "LEFT JOIN TB_ORDER_DETAIL OD ON O.ORDER_ID = OD.ORDER_ID " +
            "WHERE TO_CHAR(STAT_DATE, 'YYYY') = TO_CHAR(SYSDATE, 'YYYY') " +
            "GROUP BY YEAR", nativeQuery = true)
    OrderStats findYearlyStats();
}