package org.example.backend.model.dto.mypage;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName : org.example.backendproject.model.dto.mypage
 * fileName : PointDto
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
@Getter
@Setter
public class PointDto {
    private Integer usePointId;        // 사용 포인트 ID
    private Integer pointId;           // 포인트 ID
    private String userId;             // 유저 ID
    private Integer pointBalance;      // 잔액
    private String pointCode;          // 포인트 구분코드 (이벤트/리뷰/결제)
    private String pointExpireStatus;  // 포인트 만료여부
    private String addDate;            // 적립일
    private String delDate;            // 삭제일

}
