package org.example.backend.repository.mypage;

import org.example.backend.model.common.PtIdUseptIdPk;
import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.model.entity.DetailPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backendproject.repository.mypage
 * fileName : PointDetailRepository
 * author : GGG
 * date : 2024-04-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         GGG          최초 생성
 */
@Repository
public interface PointDetailRepository extends JpaRepository<DetailPoint, PtIdUseptIdPk> {
    @Query(value = "SELECT p.POINT_ID AS pointId, " +
            "up.USE_POINT_ID AS usePointId, " +
            "up.USE_POINT_PRICE AS usePointPrice, " +
            "up.ADD_DATE AS usePointDate, " +
            "dp.POINT_BALANCE AS pointBalance, " +
            "p.POINT_ADD AS pointAdd, " +
            "p.POINT_CODE AS pointCode, " +
            "p.ADD_DATE AS addDate, " +
            "p.DEL_DATE AS delDate " +
            "FROM TB_DETAIL_POINT dp " +
            "LEFT JOIN TB_POINT p ON dp.POINT_ID = p.POINT_ID AND p.STATUS = 'Y' " +
            "LEFT JOIN TB_USE_POINT up ON dp.USE_POINT_ID = up.USE_POINT_ID AND up.STATUS = 'Y' " +
            "WHERE p.USER_ID = :userId AND dp.STATUS = 'Y' " +
            "ORDER BY COALESCE(up.ADD_DATE, p.ADD_DATE) DESC",
            nativeQuery = true)
    List<PointDto> findPointDetailsByUserId(@Param("userId") String userId);
}
