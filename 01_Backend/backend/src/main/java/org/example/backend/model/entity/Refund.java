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
 * fileName : Refund
 * author : kimtaewan
 * date : 2024-04-25
 * description : 환불
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성
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
@IdClass(OrderIdOpIdPk.class)
public class Refund extends BaseTimeEntity {
    @Id
    private Long orderId;               // 주문 ID (PK)
    @Id
    private Long opId;                  // 옵션 ID (PK)
    private Integer refundPrice;        // 환불금액
    private String refundCode;          // 주문상태코드
    private String refundReason;       // 환불사유
    private String refundDenyReason;       // 환불철회사유
}
