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
 * fileName : OrderCancel
 * author : SAMSUNG
 * date : 2024-04-19
 * description : 주문취소 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
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
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_ORDER_CANCEL SET STATUS = 'N' WHERE ORDER_ID = ? AND OP_ID = ?")
// 복합키 클래스
@IdClass(OrderIdOpIdPk.class)
public class OrderCancel extends BaseTimeEntity2 {
    @Id
    private Integer orderId;    // 주문번호 (PK)
    @Id
    private Integer opId;       // 옵션번호 (PK)
    private Integer ocPrice;    // 취소금액
    private String ocCode;      // 주문상태코드
    private String ocReason;    // 취소사유
}
