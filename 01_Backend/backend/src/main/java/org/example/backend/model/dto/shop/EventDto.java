package org.example.backend.model.dto.shop;

import lombok.*;

/**
 * packageName : org.example.backend.model.dto.shop
 * fileName : EventDto
 * author : SAMSUNG
 * date : 2024-05-17
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-17         SAMSUNG          최초 생성
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long eventId;
    private String userId;
    private String eventTitle;
    private String eventContent;
    private String eventThumbnail;
    private String eventCode;
    private String eventImgUrl;
}
