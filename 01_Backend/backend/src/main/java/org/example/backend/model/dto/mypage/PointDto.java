package org.example.backend.model.dto.mypage;

/**
 * packageName : org.example.backend.model.dto.mypage
 * fileName : PointDto
 * author : GGG
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         GGG          최초 생성
 */

public interface PointDto {
    Integer usePointId();        // 사용 포인트 ID
    Integer pointId();           // 포인트 ID
    String userId();             // 유저 ID
    Integer pointBalance();      // 잔액
    String pointCode();          // 포인트 구분코드 (이벤트/리뷰/결제)
    String pointExpireStatus();  // 포인트 만료여부
    String addDate();            // 적립일
    String delDate();            // 삭제일
}
