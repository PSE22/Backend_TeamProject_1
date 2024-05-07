package org.example.backend.repository.mypage;

import org.example.backend.model.common.PtIdUseptIdPk;
import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.model.entity.DetailPoint;
import org.example.backend.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query(value = "SELECT *\n" +
            "FROM TB_POINT P\n" +
            "JOIN TB_DETAIL_POINT DP ON DP.POINT_ID = P.POINT_ID\n" +
            "WHERE DP.POINT_BALANCE <= 0 AND P.USER_ID = :userId", nativeQuery = true)
    List<Point> findPointByUserId(@Param("userId") String userId);

    @Query(value = "SELECT *\n" +
            "FROM TB_DETAIL_POINT DP\n" +
            "WHERE POINT_BALANCE <= 0 AND USER_ID = :userId", nativeQuery = true)
    List<DetailPoint> findDetailPointByUserId(@Param("userId") String userId);

    @Query(value = "SELECT *\n" +
            "FROM TB_POINT\n" +
            "WHERE STATUS = 'Y' AND USER_ID = :userId\n" +
            "ORDER BY POINT_ID ASC;", nativeQuery = true)
    List<DetailPoint> findPointAdd(@Param("userId") String userId);
}
