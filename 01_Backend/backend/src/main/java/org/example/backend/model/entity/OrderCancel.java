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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backend.model.entity
 * fileName : OrderCancel
 * author : kimtaewan
 * date : 2024-04-25
 * description : 주문취소
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성

 */
@Entity
@Table(name="TB_ORDER_CANCEL")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@IdClass(OrderIdOpIdPk.class)
public class OrderCancel extends BaseTimeEntity {
    @Id
    private Long orderId;           // 주문 ID
    @Id
    private Long opId;              // 옵션 ID
    private Integer ocPrice;        // 취소금액
    private String ocCode;          // 주문상태코드
    private String ocReason;        // 취소사유
    private String ocDenyReason;        // 취소철회사유
}
