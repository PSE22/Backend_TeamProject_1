package org.example.backend.repository.mypage;

import org.example.backend.model.common.PtIdUseptIdPk;
import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.model.entity.DetailPoint;
import org.example.backend.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * packageName : org.example.backend.repository
 * fileName : PointDetailRepository
 * author : kimtaewan
 * date : 2024-05-07
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-0-07         kimtaewan          최초 생성
 */
@Repository
public interface PointDetailRepository extends JpaRepository<DetailPoint, PtIdUseptIdPk> {

    @Query(value = "SELECT * FROM (" +
            "SELECT p.point_id AS pointId, " +
            "p.user_id AS userId, " +
            "p.point_add AS pointAdd, " +
            "p.point_code AS pointCode, " +
            "p.add_date AS addDate, " +
            "p.del_date AS delDate, " +
            "p.point_expire_status AS pointExpireStatus, " +
            "'적립' AS actionType, " +
            "NULL AS usePointId, NULL AS usePointPrice, " +
            "p.add_date AS orderDate " +
            "FROM tb_point p " +
            "WHERE p.user_id = :userId AND p.status = 'Y' AND p.point_expire_status = 'N' " +
            "AND p.add_date BETWEEN :startDate AND :endDate " +
            "UNION ALL " +
            "SELECT NULL AS pointId, o.user_id AS userId, NULL AS pointAdd, " +
            "NULL AS pointCode, up.add_date AS addDate, NULL AS delDate, " +
            "NULL AS pointExpireStatus, " +
            "'사용' AS actionType, " +
            "up.use_point_id AS usePointId, " +
            "up.use_point_price AS usePointPrice, " +
            "up.add_date AS orderDate " +
            "FROM tb_use_point up " +
            "JOIN tb_order o ON up.order_id = o.order_id " +
            "WHERE o.user_id = :userId " +
            "AND up.add_date BETWEEN :startDate AND :endDate " +
            "UNION ALL " +
            "SELECT p.point_id AS pointId, p.user_id AS userId, p.point_add AS pointAdd, " +
            "NULL AS pointCode, p.add_date AS addDate, p.del_date AS delDate, " +
            "p.point_expire_status AS pointExpireStatus, " +
            "'만료' AS actionType, " +
            "NULL AS usePointId, NULL AS usePointPrice, " +
            "p.del_date AS orderDate " +
            "FROM tb_point p " +
            "WHERE p.user_id = :userId AND p.status = 'N' AND p.point_expire_status = 'Y' " +
            "AND p.del_date BETWEEN :startDate AND :endDate " +
            ") ORDER BY orderDate DESC", nativeQuery = true)
    List<PointDto> findPointDetailsByUserId(@Param("userId") String userId, @Param("startDate") String startDate, @Param("endDate") String endDate);

//    delDate 가 되면 point_expire_status Y(만료) 로 변경, status N 으로 변경
    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_POINT p SET p.POINT_EXPIRE_STATUS = 'Y', p.STATUS = 'N' " +
            "WHERE p.DEL_DATE <= SYSDATE " +
            "AND p.POINT_EXPIRE_STATUS = 'N' AND p.STATUS = 'Y'", nativeQuery = true)
    int updatePointsToExpired();


    @Query(value = "SELECT UP.USE_POINT_ID,\n" +
            "       P.POINT_ID,\n" +
            "       P.POINT_ADD - UP.USE_POINT_PRICE AS POINT_BALANCE\n" +
            "FROM TB_USE_POINT UP\n" +
            "JOIN (SELECT P.POINT_ID,\n" +
            "           P.POINT_ADD,\n" +
            "           ROW_NUMBER() OVER (ORDER BY P.POINT_ID ASC) AS rn,\n" +
            "           P.POINT_ADD - UP.USE_POINT_PRICE AS POINT_BALANCE\n" +
            "    FROM TB_POINT P\n" +
            "    JOIN TB_USE_POINT UP ON 1 = 1\n" +
            "    WHERE P.STATUS = 'Y' AND UP.USER_ID = :userId) P ON P.rn = 1", nativeQuery = true)
    DetailPoint findPointBalanceByUserId(@Param("userId") String userId);

    @Query(value = "SELECT UP.USE_POINT_ID,\n" +
            "       P2.POINT_ID,\n" +
            "       CASE \n" +
            "           WHEN P.POINT_ADD - UP.USE_POINT_PRICE < 0 THEN P2.POINT_ADD + (P.POINT_ADD - UP.USE_POINT_PRICE)\n" +
            "           ELSE NULL\n" +
            "       END AS POINT_BALANCE\n" +
            "FROM TB_USE_POINT UP\n" +
            "JOIN (SELECT P.POINT_ID, P.POINT_ADD,\n" +
            "           ROW_NUMBER() OVER (ORDER BY P.POINT_ID ASC) AS rn,\n" +
            "           P.POINT_ADD - UP.USE_POINT_PRICE AS POINT_BALANCE\n" +
            "    FROM TB_POINT P\n" +
            "    JOIN TB_USE_POINT UP ON 1 = 1\n" +
            "    WHERE P.STATUS = 'Y') P ON P.rn = 1\n" +
            "JOIN (\n" +
            "    SELECT P2.POINT_ID,\n" +
            "           P2.POINT_ADD,\n" +
            "           ROW_NUMBER() OVER (ORDER BY P2.POINT_ID ASC) AS rn\n" +
            "    FROM TB_POINT P2\n" +
            "    JOIN TB_USE_POINT UP ON 1 = 1\n" +
            "    WHERE P2.STATUS = 'Y' AND UP.USER_ID = :userId\n" +
            ") P2 ON P2.rn = 2\n" +
            "WHERE P.POINT_BALANCE < 0 AND UP.USER_ID = :userId", nativeQuery = true)
    DetailPoint findUnionPointBalanceByUserId(@Param("userId") String userId);


    // 적립 포인트, 상세 포인트 테이블 조인해서 잔액이 0 또는 음수인 것만 조회
    @Query(value = "SELECT *\n" +
            "FROM TB_POINT P\n" +
            "JOIN TB_DETAIL_POINT DP ON DP.POINT_ID = P.POINT_ID\n" +
            "WHERE DP.POINT_BALANCE <= 0 AND P.USER_ID = :userId", nativeQuery = true)
    List<Point> findPointByUserId(@Param("userId") String userId);

    // 상세 포인트 테이블의 잔액이 0 또는 음수인 데이터 조회
    @Query(value = "SELECT *\n" +
            "FROM TB_DETAIL_POINT DP\n" +
            "WHERE POINT_BALANCE <= 0 AND USER_ID = :userId", nativeQuery = true)
    List<DetailPoint> findDetailPointByUserId(@Param("userId") String userId);

    // 적립 포인트 테이블에서 회원의 사용 가능한 적립금 데이터 조회
    @Query(value = "SELECT *\n" +
            "FROM TB_POINT\n" +
            "WHERE STATUS = 'Y' AND USER_ID = :userId\n" +
            "ORDER BY POINT_ID ASC", nativeQuery = true)
    List<DetailPoint> findPointAdd(@Param("userId") String userId);
}

