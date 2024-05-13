package org.example.backend.model.dto.shop;

/**
 * packageName : org.example.backend.model.dto.shop
 * fileName : IBestProductDto
 * author : sjuk2
 * date : 2024-05-12
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-12         sjuk2          최초 생성
 */
public interface IBestProductDto {
    Integer getPdId();
    String getPdName();
    String getPdThumbnail();
    String getAddDate();
    Integer getPdPrice();
}
