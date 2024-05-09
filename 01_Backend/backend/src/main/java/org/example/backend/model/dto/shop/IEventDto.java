package org.example.backend.model.dto.shop;

/**
 * packageName : org.example.backend.model.dto.shop
 * fileName : IEventDto
 * author : SAMSUNG
 * date : 2024-05-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08         SAMSUNG          최초 생성
 */
public interface IEventDto {
    String getEventTitle();
    String getEventContent();
    String getEventAddDate();
    String getEventThumbnail();
    String getEventImgUrl();
}
