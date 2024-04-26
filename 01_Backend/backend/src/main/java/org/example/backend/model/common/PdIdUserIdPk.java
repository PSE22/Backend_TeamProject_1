package org.example.backend.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.backend.model.common
 * fileName : PdIdUserIdPk
 * author : kimtaewan
 * date : 2024-04-26
 * description : 상품 ID, 회원 ID 복합키
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         kimtaewan          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PdIdUserIdPk implements Serializable {
    private Long pdId;
    private String userId;
}
