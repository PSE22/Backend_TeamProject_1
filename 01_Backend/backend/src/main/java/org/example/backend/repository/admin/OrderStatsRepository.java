package org.example.backend.repository.admin;

import org.example.backend.model.dto.admin.DailyOrderStatsDto;
import org.example.backend.model.dto.admin.MonthlyOrderStatsDto;
import org.example.backend.model.dto.admin.YearlyOrderStatsDto;
import org.example.backend.model.entity.admin.OrderStats;
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

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') AS odStatDate, " +
            "COUNT(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_ID END) - " +
            "COUNT(CASE WHEN (R.ORDER_ID IS NOT NULL AND O.ORDER_PRICE - R.REFUND_PRICE <= 0) AND R.REFUND_CODE LIKE 'OD0302%' THEN O.ORDER_ID ELSE NULL END) AS dailyOrderCnt, " +
            "SUM(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_PRICE ELSE 0 END) - " +
            "SUM(CASE WHEN R.ORDER_ID IS NOT NULL AND R.REFUND_CODE LIKE 'OD0302%' THEN R.REFUND_PRICE ELSE 0 END) AS dailySales " +
            "FROM TB_ORDER O " +
            "LEFT OUTER JOIN TB_REFUND R ON R.ORDER_ID = O.ORDER_ID " +
            "WHERE SUBSTR(O.ADD_DATE, 1, 10) = TO_CHAR(SYSDATE, 'YYYY-MM-DD') " +
            "GROUP BY TO_CHAR(SYSDATE, 'YYYY-MM-DD') ", nativeQuery = true)
    DailyOrderStatsDto findDailyStats();

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM') AS odStatDate, " +
            "COUNT(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_ID END) - " +
            "COUNT(CASE WHEN (R.ORDER_ID IS NOT NULL AND O.ORDER_PRICE - R.REFUND_PRICE <= 0) AND R.REFUND_CODE LIKE 'OD0302%' THEN O.ORDER_ID ELSE NULL END) AS monthlyOrderCnt, " +
            "SUM(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_PRICE ELSE 0 END) - " +
            "SUM(CASE WHEN R.ORDER_ID IS NOT NULL AND R.REFUND_CODE LIKE 'OD0302%' THEN R.REFUND_PRICE ELSE 0 END) AS monthlySales " +
            "FROM TB_ORDER O " +
            "LEFT OUTER JOIN TB_REFUND R ON R.ORDER_ID = O.ORDER_ID " +
            "WHERE SUBSTR(O.ADD_DATE, 1, 7) = TO_CHAR(SYSDATE, 'YYYY-MM') " +
            "GROUP BY TO_CHAR(SYSDATE, 'YYYY-MM') ", nativeQuery = true)
    MonthlyOrderStatsDto findMonthlyStats();

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY') AS odStatDate, " +
            "COUNT(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_ID END) - " +
            "COUNT(CASE WHEN (R.ORDER_ID IS NOT NULL AND O.ORDER_PRICE - R.REFUND_PRICE <= 0) AND R.REFUND_CODE LIKE 'OD0302%' THEN O.ORDER_ID ELSE NULL END) AS yearlyOrderCnt, " +
            "SUM(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_PRICE ELSE 0 END) - " +
            "SUM(CASE WHEN R.ORDER_ID IS NOT NULL AND R.REFUND_CODE LIKE 'OD0302%' THEN R.REFUND_PRICE ELSE 0 END) AS YearlySales " +
            "FROM TB_ORDER O " +
            "LEFT OUTER JOIN TB_REFUND R ON R.ORDER_ID = O.ORDER_ID " +
            "WHERE SUBSTR(O.ADD_DATE, 1, 4) = TO_CHAR(SYSDATE, 'YYYY') " +
            "GROUP BY TO_CHAR(SYSDATE, 'YYYY') ", nativeQuery = true)
    YearlyOrderStatsDto findYearlyStats();

    @Query(value = "SELECT SUBSTR(OD_STAT_DATE, 9, 2) AS odStatDate, DAILY_SALES AS dailySales, DAILY_ORDER_CNT AS dailyOrderCnt " +
            "FROM ( " +
            "    SELECT OD_STAT_DATE, DAILY_SALES, DAILY_ORDER_CNT, " +
            "           ROW_NUMBER() OVER (PARTITION BY SUBSTR(OD_STAT_DATE, 1, 7), SUBSTR(OD_STAT_DATE, 9, 2) ORDER BY OD_STAT_DATE DESC) AS RN " +
            "    FROM TB_ORDER_STATS " +
            ") " +
            "WHERE RN = 1 AND SUBSTR(OD_STAT_DATE, 1, 7) = :odStatDate", nativeQuery = true)
    List<DailyOrderStatsDto> findDailyOrderStats(@Param("odStatDate") String odStatDate);

    @Query(value = "SELECT SUBSTR(OD_STAT_DATE, 6, 2) AS odStatDate, MONTHLY_SALES AS monthlySales, MONTHLY_ORDER_CNT AS monthlyOrderCnt " +
            "FROM ( " +
            "    SELECT OD_STAT_DATE, MONTHLY_SALES, MONTHLY_ORDER_CNT, " +
            "           ROW_NUMBER() OVER (PARTITION BY SUBSTR(OD_STAT_DATE, 1, 7) ORDER BY OD_STAT_DATE DESC) AS RN " +
            "    FROM TB_ORDER_STATS " +
            ") " +
            "WHERE RN = 1 AND SUBSTR(OD_STAT_DATE, 1, 4) = :odStatDate", nativeQuery = true)
    List<MonthlyOrderStatsDto> findMonthlyOrderStats(@Param("odStatDate") String odStatDate);

    @Query(value = "SELECT SUBSTR(OD_STAT_DATE, 1, 4) AS odStatDate, YEARLY_SALES AS yearlySales, YEARLY_ORDER_CNT AS yearlyOrderCnt " +
            "FROM ( " +
            "    SELECT OD_STAT_DATE, YEARLY_SALES, YEARLY_ORDER_CNT, " +
            "           ROW_NUMBER() OVER (PARTITION BY SUBSTR(OD_STAT_DATE, 1, 4) ORDER BY OD_STAT_DATE DESC) AS RN " +
            "    FROM TB_ORDER_STATS " +
            ") " +
            "WHERE RN = 1", nativeQuery = true)
    List<YearlyOrderStatsDto> findYearlyOrderStats();
}