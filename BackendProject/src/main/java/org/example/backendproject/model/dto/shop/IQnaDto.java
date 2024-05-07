package org.example.backendproject.model.dto.shop;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : IQnaDto
 * author : sjuk2
 * date : 2024-04-24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24         sjuk2          최초 생성
 */
public interface IQnaDto {
    String getUserId();
    String getPdQnaTitle();
    String getPdQnaContent();
    String getPdAddDate();
    String getPdQnaSecret();
    String getPdQnaImgUrl();
    String getPdQnaReplyContent();
    String getQrAddDate();
}
