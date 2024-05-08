package org.example.backend.repository.mypage;

import org.example.backend.model.common.OrderIdOpIdPk;
import org.example.backend.model.dto.mypage.OrderCheckDto;
import org.example.backend.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
            "od.ORDER_DETAIL_CODE AS orderCode, " +
            "od.ADD_DATE AS addDate, " +
            "pd.PD_THUMBNAIL AS pdThumbnail, " +
            "pd.PD_NAME AS pdName, " +
            "o.ORDER_ID AS orderId " +
            "FROM TB_ORDER_DETAIL od " +
            "JOIN TB_ORDER o ON od.ORDER_ID = o.ORDER_ID " +
            "JOIN TB_OPTION opt ON od.OP_ID = opt.OP_ID " +
            "JOIN TB_PRODUCT pd ON opt.PD_ID = pd.PD_ID " +
            "WHERE o.STATUS = 'Y' " +
            "AND o.USER_ID = :userId " +
            "ORDER BY od.ADD_DATE DESC",
            nativeQuery = true)
    List<OrderCheckDto> selectOrderCheck(@Param("userId") String userId);

//    주문 카운트
    @Query(value = "SELECT count(*) FROM TB_ORDER o\n" +
            "WHERE o.STATUS = 'Y'\n" +
            "AND o.USER_ID = :userId\n" +
            "ORDER BY o.ADD_DATE DESC",
            nativeQuery = true)
    Integer orderCount (@Param("userId") String userId);
}