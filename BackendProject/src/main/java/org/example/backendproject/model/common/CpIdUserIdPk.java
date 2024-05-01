package org.example.backendproject.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.backendproject.model.common
 * fileName : CpIdUserIdPk
 * author : SAMSUNG
 * date : 2024-04-23
 * description :
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
public class CpIdUserIdPk implements Serializable {
    private Long cpId;       // 쿠폰 ID
    private String userId;      // 회원 ID
}
