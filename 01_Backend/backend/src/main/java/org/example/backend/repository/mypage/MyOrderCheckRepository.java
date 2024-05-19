package org.example.backend.repository.mypage;

import org.example.backend.model.common.OrderIdOpIdPk;
import org.example.backend.model.dto.mypage.OrderCheckDto;
import org.example.backend.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName : org.example.backend.repository.mypage
 * fileName : MyOrderCheck
 * author : GGG
 * date : 2024-05-02
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02         GGG          최초 생성
 */
@Repository
public interface MyOrderCheckRepository extends JpaRepository<OrderDetail, OrderIdOpIdPk> {
    @Query(value = "SELECT od.ORDER_DETAIL_CNT AS orderDetailCnt, " +
            "od.ORDER_DETAIL_PRICE AS orderDetailPrice, " +
            "o.ADD_DATE AS addDate, " +
            "pd.PD_THUMBNAIL AS pdThumbnail, " +
            "pd.PD_NAME AS pdName, " +
            "pd.PD_ID AS pdId, " +
            "o.ORDER_ID AS orderId, " +
            "cm.CM_CD_NAME AS cmCdName " +
            "FROM TB_ORDER_DETAIL od " +
            "JOIN TB_ORDER o ON od.ORDER_ID = o.ORDER_ID " +
            "JOIN TB_OPTION opt ON od.OP_ID = opt.OP_ID " +
            "JOIN TB_PRODUCT pd ON opt.PD_ID = pd.PD_ID " +
            "JOIN TB_CM_CODE cm ON o.ORDER_CODE = cm.CM_CD " +
            "WHERE o.USER_ID = :userId " +
            "AND o.ADD_DATE BETWEEN :startDate AND :endDate " +
            "ORDER BY o.ADD_DATE DESC", nativeQuery = true)
    List<OrderCheckDto> findOrdersByDateRange(@Param("userId") String userId,
                                              @Param("startDate") String startDate,
                                              @Param("endDate") String endDate);

//    주문 카운트
    @Query(value = "SELECT count(*) FROM TB_ORDER\n" +
            "WHERE USER_ID = :userId\n" +
            "AND STATUS='Y'",
            nativeQuery = true)
    Integer orderCount (@Param("userId") String userId);
}