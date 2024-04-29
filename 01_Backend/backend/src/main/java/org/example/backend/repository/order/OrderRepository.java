package org.example.backend.repository.order;

import org.example.backend.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = "SELECT * FROM TB_ORDER\n" +
            "WHERE ORDER_ID LIKE '%' || :orderId || '%'"
            ,countQuery = "SELECT count(*) FROM TB_ORDER\n" +
            "WHERE ORDER_ID LIKE '%' || :orderId || '%'"
            ,nativeQuery = true
    )
    Page<Order> findAllByOrderId(@Param("orderId") Long orderId,
                                                 Pageable pageable
    );
}
