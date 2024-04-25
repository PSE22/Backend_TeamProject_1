package org.example.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity2;
import org.example.backend.model.common.OrderIdOpIdPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backend.model.entity
 * fileName : Refund
 * author : SAMSUNG
 * date : 2024-04-25
 * description : 환불 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         kimtaewan          최초 생성
 */
@Entity
@Table(name="TB_REFUND")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_REFUND SET STATUS = 'N' WHERE ORDER_ID = ? AND OP_ID = ?")
@IdClass(OrderIdOpIdPk.class)
public class Refund extends BaseTimeEntity2 {

    @Id
    private Integer orderId;        // 주문번호 (PK)
    @Id
    private Integer opId;           // 옵션번호 (PK)
    private Integer refundPrice;    // 환불금액
    private Integer refundCode;     // 주문상태코드
    private Integer refundReason;   // 취소사유
}
