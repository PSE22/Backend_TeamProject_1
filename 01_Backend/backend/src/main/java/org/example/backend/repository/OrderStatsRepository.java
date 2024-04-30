package org.example.backend.repository;

import org.example.backend.model.entity.OrderStats;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface OrderStatsRepository extends JpaRepository<OrderStats, Long> {
}
