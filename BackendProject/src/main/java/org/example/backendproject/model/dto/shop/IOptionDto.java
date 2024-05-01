package org.example.backendproject.model.dto.shop;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : IOptionDto
 * author : sjuk2
 * date : 2024-04-30
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30         sjuk2          최초 생성
 */
public interface IOptionDto {
    Integer getPdid();
    String getOptionName();
    Integer getOptionPrice();
    Integer getOptionStock();
}
