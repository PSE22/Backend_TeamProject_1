package org.example.backendproject.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.backendproject.model.common
 * fileName : OrderIdOpIdPk
 * author : SAMSUNG
 * date : 2024-04-23
 * description : 주문 ID, 옵션 ID 복합키 클래스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-23         SAMSUNG          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderIdOpIdPk implements Serializable {
    private Integer orderId;        // 주문 ID
    private Integer opId;           // 옵션 ID
}
