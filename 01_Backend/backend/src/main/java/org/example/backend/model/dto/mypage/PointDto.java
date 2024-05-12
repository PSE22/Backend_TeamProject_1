package org.example.backend.model.dto.mypage;

import java.time.LocalDate;

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
    Integer getPointId();
    Integer getUsePointId();
    Integer getPointBalance();  // 잔액
    Integer getUsePointPrice(); // 차감금액
    Integer getPointAdd();      // 적립금액
    String getPointCode();      // 포인트 구분코드 (이벤트/리뷰/결제)
    String getAddDate();        // 포인트 적립일
    String getUsePointDate();   // 포인트 사용일
    String getDelDate();        // 포인트 만료일

     String getActionType(); // '적립', '사용', '만료'

}
