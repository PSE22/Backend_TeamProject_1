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
 * fileName : OrderCancel
 * author : kimtaewan
 * date : 2024-04-25
 * description : 주문취소
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         kimtaewan          최초 생성
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
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_ORDER_CANCEL SET STATUS = 'N' WHERE ORDER_ID = ? AND OP_ID = ?")
@IdClass(OrderIdOpIdPk.class)
public class OrderCancel extends BaseTimeEntity2 {

    @Id
    private Integer orderId;
    @Id
    private Integer opId;
    private Integer ocPrice;
    private String ocCode;
    private String ocReason;
}
