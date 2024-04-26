package org.example.backendproject.model.dto.shop;

/**
 * packageName : org.example.backendproject.model.dto.shop
 * fileName : IReviewDto
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
public interface IReviewDto {
    String getUserId(); // 회원 ID
    String getReviewTitle(); // 리뷰 제목
    String getReviewContent(); // 리뷰 내용
    Integer getReviewRate(); // 평점
    String getAddDate(); // 등록일
    String getReviewImgUrl(); // 이미지
}
