package org.example.backend.model.dto.mypage;

/**
 * packageName : org.example.backendproject.model.dto.mypage
 * fileName : WishListDto
 * author : GGG
 * date : 2024-04-24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24         GGG          최초 생성
 */
public interface WishlistDto {
    Long getPdId();  // pdId
    String getPdName(); // 상품명
    Integer getPdPrice(); // 상품가격
    String getPdThumbnail(); // 상품이미지

}
