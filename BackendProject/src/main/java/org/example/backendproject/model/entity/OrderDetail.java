package org.example.backendproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.example.backendproject.model.common.OrderIdOpIdPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : OrderDetail
 * author : SAMSUNG
 * date : 2024-04-19
 * description : 주문상세 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
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
// 복합키 클래스
@IdClass(OrderIdOpIdPk.class)
public class OrderDetail {
    @Id
    private Integer orderId;            // 주문번호 (PK)
    @Id
    private Integer opId;               // 옵션번호 (PK)
    private Integer orderDetailCnt;     // 수량
    private Integer orderDetailPrice;   // 금액
}
