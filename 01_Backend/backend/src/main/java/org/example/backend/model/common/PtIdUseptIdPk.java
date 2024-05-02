package org.example.backend.model.common;

import lombok.*;

import java.io.Serializable;

/**
 * packageName : org.example.backendproject.model.common
 * fileName : PtIdUseptIdPk
 * author : GGG
 * date : 2024-04-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         GGG          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PtIdUseptIdPk implements Serializable {
    private Integer usePointId; // 사용 포인트 ID
    private Integer pointId;    // 포인트 ID
    private Integer pointBalance; // 포인트 잔액
}
