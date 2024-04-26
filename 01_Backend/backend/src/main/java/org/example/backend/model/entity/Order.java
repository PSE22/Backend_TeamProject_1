package org.example.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backend.model.entity
 * fileName : Order
 * author : kimtaewan
 * date : 2024-04-25
 * description : 주문
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성
 */
@Entity
@Table(name="TB_ORDER")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_ORDER SET STATUS = 'N' WHERE ORDER_ID = ?")
public class Order extends BaseTimeEntity2 {

    @Id
    private Integer orderId;
    private String userId;
    private String orderName;
    private String orderAddr;
    private String orderPhone;
    private String orderMemo;
    private String shipMemo;
    private Integer orderPrice;
    private String orderPayment;
    private String orderCode;
}
