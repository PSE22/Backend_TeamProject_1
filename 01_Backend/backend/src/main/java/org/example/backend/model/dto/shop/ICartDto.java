package org.example.backend.model.dto.shop;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : ICartDto
 * author : gumiji
 * date : 4/23/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/23/24         gumiji          최초 생성
 */
public interface ICartDto {
    Integer getCartId(); // 장바구니 번호
    Integer getOpId(); // 장바구니 번호
    String getUserId(); // 장바구니 번호
    Integer getCartCount(); // 장바구니 수량
    String getOpName();     // 옵션명
    Integer getOpPrice();   // 옵션 가격
    String getPdName();     // 상품명
    Integer getPdPrice();   // 상품 가격
    String getPdThumbnail();   // 상품 이미지
    Long getPdId();   // 상품 번호

}
