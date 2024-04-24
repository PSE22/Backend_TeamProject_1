package org.example.backendproject.service.shop;

import org.example.backendproject.model.dto.shop.IReviewDto;
import org.example.backendproject.model.entity.PdReview;
import org.example.backendproject.repository.shop.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : ReviewService
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
@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

//    TODO: 저장(수정) 함수
    public PdReview save(PdReview pdReview) {
//         DB 저장 함수
        PdReview pdReview2 = reviewRepository.save(pdReview);
        return pdReview2;
    }

//    TODO: 전체 조회 함수
    public Page<IReviewDto> selectByReviewContaining(
            Integer reviewId,
            Pageable pageable
    ) {
        Page<IReviewDto> page = reviewRepository.selectByReviewContaining(reviewId, pageable);
        return page;
    }
}
