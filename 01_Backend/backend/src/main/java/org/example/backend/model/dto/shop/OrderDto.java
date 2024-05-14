package org.example.backend.model.dto.shop;

import lombok.*;
import org.example.backend.model.entity.OrderDetail;

import java.util.List;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : OrderDto
 * author : SAMSUNG
 * date : 2024-05-05
 * description : 프론트에서 전달한 주문 객체를 담을 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-05         SAMSUNG          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;                           // 주문번호 (PK)
    private List<OrderDetail> orderDetailList;      // 주문 상세 List
    private String userId;                          // 회원 ID (FK)
    private String orderName;                       // 수령인
    private String orderAddr;                       // 배송지 주소
    private String orderPhone;                      // 수령인 연락처
    private String shipMemo;                        // 배송메모
    private Integer orderPrice;                     // 결제금액
    private String orderPayment;                    // 결제 수단
    private String orderCode;                       // 주문상태코드
    private String postcode;                        // 우편번호
}
