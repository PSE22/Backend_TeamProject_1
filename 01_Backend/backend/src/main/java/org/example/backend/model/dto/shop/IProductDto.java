package org.example.backend.model.dto.shop;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : IProductDto
 * author : SAMSUNG
 * date : 2024-04-24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24         SAMSUNG          최초 생성
 */
public interface IProductDto {
    Integer getPdId();    // 상품번호
    String getPdName();  // 상품명
    Integer getPdPrice();     // 상품 가격
    Integer getPdStock();    // 상품재고
    String getAddDate(); // 상품등록일
    String getCategoryCode();   // 카테고리코드
    String getPdImgUrl();   // 상품이미지
    Integer getOpId();  // 옵션 ID
    String getOpName(); // 옵션명
    Integer getOpPrice();   // 옵션추가금액
    String getPdThumbnail();
}
