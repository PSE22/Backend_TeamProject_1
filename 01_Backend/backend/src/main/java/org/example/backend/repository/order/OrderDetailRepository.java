package org.example.backend.repository.order;

import org.example.backend.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName : org.example.backend.repository
 * fileName : OrderDetailRepository
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
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = "SELECT * FROM TB_ORDER_DETAIL\n" +
            "WHERE ORDER_ID = :orderId",
    nativeQuery = true)
    List<OrderDetail> findByOrderIdAndOpId(@Param("orderId") Long orderId);
    List<OrderDetail> findByOrderId(Long orderId);
}
