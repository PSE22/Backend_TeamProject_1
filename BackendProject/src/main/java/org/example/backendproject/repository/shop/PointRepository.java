package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : PointRepository
 * author : 5
 * date : 24. 5. 2.
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.         5          최초 생성
 */
@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

}
