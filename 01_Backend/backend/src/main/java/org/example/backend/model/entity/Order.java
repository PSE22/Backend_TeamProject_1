package org.example.backend.model.entity;

import jakarta.persistence.*;
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
@Table(name = "TB_ORDER")
@SequenceGenerator(
        name = "SEQ_TB_ORDER_ORDER_ID_GENERATOR"
        , sequenceName = "SEQ_TB_ORDER_ORDER_ID"
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_ORDER SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE ORDER_ID = ?")
public class Order extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    , generator = "SEQ_TB_ORDER_ORDER_ID_GENERATOR")
    private Long orderId;           // 주문번호 (PK)
    private String userId;          // 회원 ID (FK)
    private String orderName;       // 수령인
    private String orderAddr;       // 배송지 주소
    private String orderPhone;      // 수령인 연락처
    private String shipMemo;        // 배송메모
    private Integer orderPrice;     // 결제금액
    private String orderPayment;    // 결제 수단
    private String orderCode;       // 주문상태코드
    private String postcode;        // 우편번호
}
