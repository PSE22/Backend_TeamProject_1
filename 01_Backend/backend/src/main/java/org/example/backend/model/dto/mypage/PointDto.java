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
    Integer getUsePointId();        // 사용 포인트 ID
    Integer getPointId();           // 포인트 ID
    String getUserId();             // 유저 ID
    Integer getPointBalance();      // 잔액
    String getPointCode();          // 포인트 구분코드 (이벤트/리뷰/결제)
    String getPointExpireStatus();  // 포인트 만료여부
    String getAddDate();            // 적립일
    String getDelDate();            // 삭제일
}
