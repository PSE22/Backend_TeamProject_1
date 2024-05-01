package org.example.backend.repository.mypage;

import org.example.backend.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.mypage
 * fileName : PointRepository
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
public interface PointRepository extends JpaRepository<Point, String> {
    @Query(value = "SELECT SUM(p.pointAdd) AS pointAdd FROM Point p " +
            "WHERE p.userId = :userId " +
            "AND p.status = 'Y'")
    Integer findTotalPointsByUserId(@Param("userId") String userId);
}
