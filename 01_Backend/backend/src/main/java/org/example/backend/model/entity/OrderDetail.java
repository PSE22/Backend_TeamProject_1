package org.example.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity;
import org.example.backend.model.common.OrderIdOpIdPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.backend.model.entity
 * fileName : OrderDetail
 * author : kimtaewan
 * date : 2024-04-25
 * description : 주문상세
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성

 */
@Entity
@Table(name="TB_ORDER_DETAIL")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@IdClass(OrderIdOpIdPk.class)
public class OrderDetail extends BaseTimeEntity {
    @Id
    private Long orderId;               // 주문 ID
    @Id
    private Long opId;                  // 옵션 ID
    private Integer orderDetailCnt;     // 주문상세수량
    private Integer orderDetailPrice;   // 주문상세가격
    private String orderDetailCode;     // 주문상태코드
}
