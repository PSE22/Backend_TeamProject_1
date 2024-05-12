package org.example.backend.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.backend.model.common
 * fileName : CpIdUserIdPk
 * author : kimtaewan
 * date : 2024-04-26
 * description :
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
public class CpIdUserIdPk implements Serializable {
    private Long cpId;          // 쿠폰 ID
    private String userId;      // 회원 ID
}
