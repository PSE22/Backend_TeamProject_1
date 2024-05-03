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

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') AS OD_STAT_DATE, " +
            "COUNT(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_ID END) - " +
            "COUNT(CASE WHEN (R.ORDER_ID IS NOT NULL AND O.ORDER_PRICE - R.REFUND_PRICE <= 0) AND R.REFUND_CODE LIKE 'OD0302%' THEN O.ORDER_ID ELSE NULL END) AS DAILY_ORDER_CNT, " +
            "SUM(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_PRICE ELSE 0 END) - " +
            "SUM(CASE WHEN R.ORDER_ID IS NOT NULL AND R.REFUND_CODE LIKE 'OD0302%' THEN R.REFUND_PRICE ELSE 0 END) AS DAILY_SALES " +
            "FROM TB_ORDER_STATS OS " +
            "RIGHT OUTER JOIN TB_ORDER O ON OS.OD_STAT_DATE = O.ADD_DATE" +
            "LEFT OUTER JOIN TB_REFUND R ON R.ORDER_ID = O.ORDER_ID" +
            "WHERE O.ADD_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') " +
            "GROUP BY OD_STAT_DATE", nativeQuery = true)
    OrderStats findDailyStats();

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM') AS OD_STAT_DATE, " +
            "COUNT(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_ID END) - " +
            "COUNT(CASE WHEN (R.ORDER_ID IS NOT NULL AND O.ORDER_PRICE - R.REFUND_PRICE <= 0) AND R.REFUND_CODE LIKE 'OD0302%' THEN O.ORDER_ID ELSE NULL END) AS MONTHLY_ORDER_CNT, " +
            "SUM(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_PRICE ELSE 0 END) - " +
            "SUM(CASE WHEN R.ORDER_ID IS NOT NULL AND R.REFUND_CODE LIKE 'OD0302%' THEN R.REFUND_PRICE ELSE 0 END) AS MONTHLY_SALES " +
            "FROM TB_ORDER_STATS OS " +
            "RIGHT OUTER JOIN TB_ORDER O ON OS.OD_STAT_DATE = TO_CHAR(TO_DATE(O.ADD_DATE, 'YYYY-MM-DD'), 'YYYY-MM') " +
            "LEFT OUTER JOIN TB_REFUND R ON R.ORDER_ID = O.ORDER_ID " +
            "WHERE TO_CHAR(TO_DATE(O.ADD_DATE, 'YYYY-MM-DD'), 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM') " +
            "GROUP BY OD_STAT_DATE ", nativeQuery = true)
    OrderStats findMonthlyStats();

    @Query(value = "SELECT TO_CHAR(SYSDATE, 'YYYY') AS OD_STAT_DATE, " +
            "COUNT(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_ID END) - " +
            "COUNT(CASE WHEN (R.ORDER_ID IS NOT NULL AND O.ORDER_PRICE - R.REFUND_PRICE <= 0) AND R.REFUND_CODE LIKE 'OD0302%' THEN O.ORDER_ID ELSE NULL END) AS YEARLY_ORDER_CNT, " +
            "SUM(CASE WHEN O.ORDER_CODE LIKE 'OD01%' THEN O.ORDER_PRICE ELSE 0 END) - " +
            "SUM(CASE WHEN R.ORDER_ID IS NOT NULL AND R.REFUND_CODE LIKE 'OD0302%' THEN R.REFUND_PRICE ELSE 0 END) AS YEARLY_SALES " +
            "FROM TB_ORDER_STATS OS " +
            "RIGHT OUTER JOIN TB_ORDER O ON OS.OD_STAT_DATE = TO_CHAR(TO_DATE(O.ADD_DATE, 'YYYY-MM-DD'), 'YYYY') " +
            "LEFT OUTER JOIN TB_REFUND R ON R.ORDER_ID = O.ORDER_ID " +
            "WHERE TO_CHAR(TO_DATE(O.ADD_DATE, 'YYYY-MM-DD'), 'YYYY') = TO_CHAR(SYSDATE, 'YYYY') " +
            "GROUP BY OD_STAT_DATE ", nativeQuery = true)
    OrderStats findYearlyStats();
}