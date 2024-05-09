package org.example.backend.model.dto.admin;

/**
 * packageName : org.example.backend.model.dto.admin
 * fileName : OrderStatsDto
 * author : kimtaewan
 * date : 2024-05-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08         kimtaewan          최초 생성
 */
public interface DailyOrderStatsDto {
    String getOdStatDate();
    int getDailySales();
    int getDailyOrderCnt();
}
