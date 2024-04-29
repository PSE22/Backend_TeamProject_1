package org.example.backend.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.backend.model.common
 * fileName : OrderIdOpIdPk
 * author : kimtaewan
 * date : 2024-04-25
 * description : 주문 ID, 옵션 ID 복합키
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-23         kimtaewan          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderIdOpIdPk implements Serializable {
    private Long orderId;
    private Long opId;
}
