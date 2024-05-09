package org.example.backend.model.dto.admin;

/**
 * packageName : org.example.backend.model.dto.admin
 * fileName : MonthlyOrderStatsDto
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
public interface MonthlyOrderStatsDto {
    String getOdStatDate();
    int getMonthlySales();
    int getMonthlyOrderCnt();
}
