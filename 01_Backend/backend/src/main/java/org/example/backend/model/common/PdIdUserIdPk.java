package org.example.backend.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.backendproject.model.common
 * fileName : PdIdUserIdPk
 * author : SAMSUNG
 * date : 2024-04-23
 * description : 상품 ID, 회원 ID 복합키 클래스
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
public class PdIdUserIdPk implements Serializable {
    private Long pdId;       // 상품 ID
    private String userId;      // 회원 ID
}
