package org.example.backend.repository.order;

import org.example.backend.model.dto.mypage.IOrderCodeDto;
import org.example.backend.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM TB_ORDER\n" +
            "WHERE ORDER_ID LIKE '%' || :orderId || '%'"
            , countQuery = "SELECT count(*) FROM TB_ORDER\n" +
            "WHERE ORDER_ID LIKE '%' || :orderId || '%'"
            , nativeQuery = true
    )
    Page<Order> findAllByOrderId(@Param("orderId") Long orderId,
                                 Pageable pageable
    );

    //    주문정보 카운트
    @Query(value = "SELECT ORDER_CODE AS orderCode, COUNT(*) AS count FROM TB_ORDER\n " +
            "WHERE USER_ID = :userId " +
            "AND ORDER_CODE IN ('PO03', 'OD0101', 'OD0103', 'OD0104')\n" +
            "GROUP BY ORDER_CODE ",
            nativeQuery = true)
    List<IOrderCodeDto> orderCodeCount(@Param("userId") String userId);
}
