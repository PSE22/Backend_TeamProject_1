package org.example.backend.repository;

import org.example.backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : org.example.backend.repository
 * fileName : OrderRepository
 * author : kimtaewan
 * date : 2024-04-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
