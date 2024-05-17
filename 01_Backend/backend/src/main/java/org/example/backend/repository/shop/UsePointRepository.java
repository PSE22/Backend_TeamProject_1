package org.example.backend.repository.shop;

import org.example.backend.model.entity.UsePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backend.repository.shop
 * fileName : UsePointRepository
 * author : SAMSUNG
 * date : 2024-05-17
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-17         SAMSUNG          최초 생성
 */
@Repository
public interface UsePointRepository extends JpaRepository<UsePoint, Long> {
}
