package org.example.backend.repository.mypage;

import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backendproject.repository.mypage
 * fileName : MyPointRepository
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
public interface MyPointRepository extends JpaRepository<Point, String> {
    // 적립된 포인트 조회 (특정 사용자, 만료되지 않은 포인트)
    @Query(value = "SELECT p.POINT_ID AS pointId, " +
            "p.USER_ID AS userId, " +
            "p.POINT_BALANCE AS pointBalance, " +
            "p.POINT_CODE AS pointCode, " +
            "p.POINT_EXPIRE_STATUS AS pointExpireStatus, " +
            "p.ADD_DATE AS addDate, " +
            "p.DEL_DATE AS delDate " +
            "FROM TB_POINT p WHERE p.STATUS = 'Y' " +
            "AND p.POINT_ADD > 0 AND " +
            "p.USER_ID = :userId AND p." +
            "POINT_EXPIRE_STATUS = 'N' " +
            "ORDER BY p.ADD_DATE DESC"
            , nativeQuery = true)
    List<PointDto> findAllAddedPointByUserId(@Param("userId") String userId);

    // 사용된 포인트 조회 (특정 사용자)
    @Query(value = "SELECT p.POINT_ID AS pointId, " +
            "p.USER_ID AS userId, " +
            "p.POINT_BALANCE AS pointBalance, " +
            "p.POINT_CODE AS pointCode, " +
            "p.POINT_EXPIRE_STATUS AS pointExpireStatus, " +
            "p.ADD_DATE AS addDate, " +
            "p.DEL_DATE AS delDate " +
            "FROM TB_POINT p JOIN TB_USE_POINT up ON p.POINT_ID = up.POINT_ID " +
            "WHERE p.STATUS = 'Y' " +
            "AND up.STATUS = 'Y' " +
            "AND p.USER_ID = :userId " +
            "ORDER BY up.ADD_DATE DESC"
            , nativeQuery = true)
    List<PointDto> findAllUsedPointByUserId(@Param("userId") String userId);

    // 만료된 포인트 조회 (특정 사용자, 만료된 포인트만 조회)
    @Query(value = "SELECT p.POINT_ID AS pointId, " +
            "p.USER_ID AS userId, " +
            "p.POINT_BALANCE AS pointBalance, " +
            "p.POINT_CODE AS pointCode, " +
            "p.POINT_EXPIRE_STATUS AS pointExpireStatus, " +
            "p.ADD_DATE AS addDate, " +
            "p.DEL_DATE AS delDate FROM TB_POINT p WHERE p.STATUS = 'Y' AND p.POINT_EXPIRE_STATUS = 'Y' AND " +
            "p.USER_ID = :userId ORDER BY p.DEL_DATE DESC", nativeQuery = true)
    List<PointDto> findAllExpiredPointByUserId(@Param("userId") String userId);
}
