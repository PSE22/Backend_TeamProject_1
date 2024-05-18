package org.example.backend.repository.mypage;

import org.example.backend.model.dto.mypage.IMyOrderDetailDto;
import org.example.backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.repository.mypage
 * fileName : MyOrderDetailRepository
 * author : gumiji
 * date : 5/10/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/10/24         gumiji          최초 생성
 */
@Repository
public interface MyOrderDetailRepository extends JpaRepository<Order, Long> {
//    배송지
    @Query(value = "SELECT DISTINCT\n" +
            "O.ORDER_ID AS orderId,\n" +
            "O.ORDER_ADDR AS orderAddr,\n" +
            "O.ORDER_NAME AS orderName,\n" +
            "O.POSTCODE AS postcode,\n" +
            "O.ORDER_PHONE AS orderPhone,\n" +
            "O.SHIP_MEMO AS shipMemo,\n" +
            "O.ORDER_PRICE AS orderPrice,\n" +
            "O.ORDER_PAYMENT AS orderPayment,\n" +
            "O.ADD_DATE AS orAddDate,\n" +
            "O.ORDER_CODE AS orderCode,\n" +
            "OC.ADD_DATE AS ocAddDate,\n" +
            "OC.OC_REASON AS ocReason,\n" +
            "OC.OC_DENY_REASON AS ocDenyReason,\n" +
            "RE.ADD_DATE AS reAddDate,\n" +
            "RE.REFUND_REASON AS refundReason,\n" +
            "RE.REFUND_DENY_REASON AS refundDenyReason\n" +
            "FROM TB_ORDER O, TB_ORDER_DETAIL OD, TB_ORDER_CANCEL OC, TB_REFUND RE\n" +
            "WHERE O.ORDER_ID = OC.ORDER_ID(+)\n" +
            "AND O.ORDER_ID = OD.ORDER_ID\n" +
            "AND O.ORDER_ID = RE.ORDER_ID(+)\n" +
            "AND O.ORDER_ID = :orderId",
    nativeQuery = true)
    Optional<IMyOrderDetailDto> findByOrderId(@Param("orderId") Long orderId );

//    주문 상품리스트
    @Query(value = "SELECT\n" +
            "P.PD_NAME AS pdName,\n" +
            "P.PD_THUMBNAIL AS pdThumbnail,\n" +
            "P.PD_PRICE AS pdPrice,\n" +
            "OP.OP_NAME AS opName,\n" +
            "OD.ORDER_DETAIL_CNT AS orderDetailCnt,\n" +
            "OD.ORDER_DETAIL_PRICE AS orderDetailPrice\n" +
            "FROM TB_PRODUCT P, TB_OPTION OP, TB_ORDER_DETAIL OD, TB_ORDER O\n" +
            "WHERE P.PD_ID = OP.PD_ID\n" +
            "AND OP.OP_ID = OD.OP_ID\n" +
            "AND O.ORDER_ID = OD.ORDER_ID\n" +
            "AND O.ORDER_ID = :orderId",
    nativeQuery = true)
    List<IMyOrderDetailDto> findByOrderList(@Param("orderId") Long orderId);

//    주문 금액
    @Query(value = "SELECT\n" +
            "C.CP_DC_PRICE AS cpDcPrice,\n" +
            "C.CP_DC_RATE AS cpDcRate,\n" +
            "UP.USE_POINT_PRICE AS usePointPrice\n" +
            "FROM TB_USER_COUPON UC, TB_COUPON C, TB_ORDER O, TB_USE_POINT UP\n" +
            "WHERE UC.CP_ID = C.CP_ID(+)\n" +
            "AND UC.ORDER_ID(+) = O.ORDER_ID\n" +
            "AND O.ORDER_ID = UP.ORDER_ID(+)\n" +
            "AND O.ORDER_ID = :orderId",
    nativeQuery = true)
    Optional<IMyOrderDetailDto> findByOrderPrice(@Param("orderId") Long orderId);
}
