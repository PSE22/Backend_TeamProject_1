package org.example.backendproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : Order
 * author : SAMSUNG
 * date : 2024-04-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
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
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_ORDER SET STATUS = 'N' WHERE ORDER_ID = ?")
public class Order extends BaseTimeEntity2 {
//    order_id	number
//    user_id	varchar2(100 byte)
//    order_name	varchar2(100 byte)
//    order_addr	varchar2(500 byte)
//    order_phone	varchar2(100 byte)
//    order_memo	varchar2(1000 byte)
//    ship_memo	varchar2(1000 byte)
//    order_price	number
//    order_payment	varchar2(500 byte)
//    add_date	date
//    order_code	varchar2(100 byte)
//    status	char(1 byte)

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
