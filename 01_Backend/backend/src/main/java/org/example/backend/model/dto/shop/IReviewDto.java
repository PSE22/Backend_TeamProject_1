package org.example.backend.model.dto.shop;

/**
 * packageName : org.example.backend.model.dto.mypage
 * fileName : IReviewDto
 * author : gumiji
 * date : 5/3/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/3/24         gumiji          최초 생성
 */
public interface IReviewDto {
    Long getReviewId();
    String getUserId(); // 회원 ID
    String getReviewTitle(); // 리뷰 제목
    String getReviewContent(); // 리뷰 내용
    Integer getReviewRate(); // 평점
    String getAddDate(); // 등록일
    String getReviewImgUrl(); // 리뷰이미지
    String getPdName(); // 상품 이름
    String getPdThumbnail(); // 상품이미지
    Long getPdId();    // 상품 번호
}
