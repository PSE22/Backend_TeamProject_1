package org.example.backendproject.repository.mypage;

import org.example.backendproject.model.common.PtIdUseptIdPk;
import org.example.backendproject.model.entity.DetailPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
//    @Query("SELECT SUM(p.pointBalance) " +
//            "FROM DetailPoint p " +
//            "WHERE p.userId = :userId")
//    Integer findTotalBalanceByUserId(@Param("userId") String userId);
}
