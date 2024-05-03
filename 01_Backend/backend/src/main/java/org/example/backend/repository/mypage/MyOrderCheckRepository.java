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
    @Query(value = "SELECT od.ORDER_DETAIL_CNT AS orderDetailCnt,\n" +
            "            od.ORDER_DETAIL_PRICE AS orderDetailPrice,\n" +
            "            od.ORDER_DETAIL_CODE AS orderCode,\n" +
            "            od.ADD_DATE AS addDate,\n" +
            "            pd.PD_THUMBNAIL AS pdThumbnail,\n" +
            "            pd.PD_NAME AS pdName,\n" +
            "            o.ORDER_ID AS orderId\n" +
            "            FROM TB_ORDER_DETAIL od, TB_ORDER o, TB_PRODUCT pd\n" +
            "            WHERE od.ORDER_ID = o.ORDER_ID \n" +
            "            AND pd.USER_ID = o.USER_ID\n" +
            "            AND o.STATUS = 'Y'\n" +
            "            AND o.USER_ID = :userId\n" +
            "        ORDER BY od.ADD_DATE DESC ",
            nativeQuery = true)
    List<OrderCheckDto> selectOrderCheck(@Param("userId") String userId);



}