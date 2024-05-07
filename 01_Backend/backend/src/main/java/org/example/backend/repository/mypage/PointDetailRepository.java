package org.example.backend.repository.mypage;

import org.example.backend.model.common.PtIdUseptIdPk;
import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.model.entity.DetailPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Query(value = "SELECT p.point_id AS pointId, " +
            "p.user_id AS userId, " +
            "p.point_add AS pointAdd, " +
            "p.point_code AS pointCode, " +
            "p.add_date AS addDate, " +
            "p.del_date AS delDate, " +
            "p.point_expire_status AS pointExpireStatus, " +
            "'적립' AS actionType, " +
            "NULL AS usePointId, NULL AS usePointPrice " +
            "FROM tb_point p " +
            "WHERE p.user_id = :userId AND p.status = 'Y' " +
            "UNION ALL " +
            "SELECT NULL AS pointId, up.user_id AS userId, NULL AS pointAdd, " +
            "NULL AS pointCode, " +
            "up.add_date AS addDate, " +
            "NULL AS delDate, " + // 사용 테이블에는 만료일이 없음
            "NULL AS pointExpireStatus, " +
            "'사용' AS actionType, " +
            "up.use_point_id AS usePointId, up.use_point_price AS usePointPrice " +
            "FROM tb_use_point up " +
            "WHERE up.user_id = :userId " +
            "ORDER BY addDate DESC",
            nativeQuery = true)
    List<PointDto> findPointDetailsByUserId(@Param("userId") String userId);

//    delDate 가 되면 point_expire_status Y(만료) 로 변경, status N 으로 변경
    @Modifying
    @Transactional
    @Query(value = "UPDATE Point p SET p.point_expire_status = 'N' " +
            "WHERE p.delDate <= SYSDATE " +
            "AND p.point_expire_status = 'Y' AND p.status = 'N'", nativeQuery = true)
    int updatePointsToExpired();
}