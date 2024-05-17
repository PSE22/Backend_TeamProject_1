package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.shop.IReviewDto;
import org.example.backend.model.entity.PdReview;
import org.example.backend.service.shop.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : ReviewController
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
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

//    TODO: 저장 함수
    @PostMapping("/product/review")
    public ResponseEntity<Object> create(
            @RequestBody PdReview pdReview
    ) {
        try {
//            저장 서비스 실행
            PdReview pdReview2 = reviewService.save(pdReview);
            return new ResponseEntity<>(pdReview2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: 전체 조회 함수 + like 검색
    @GetMapping("/productReview")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") long pdId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<IReviewDto> reviewDtoPage
                    = reviewService.selectByReviewContaining(pdId, pageable);

//            공통 페이징 객체 생성 : 자료 구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("review", reviewDtoPage.getContent()); // review 배열
            response.put("currentPage", reviewDtoPage.getNumber()); // 현재페이지번호
            response.put("totalItems", reviewDtoPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", reviewDtoPage.getTotalPages()); // 총페이지수

            if (reviewDtoPage.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
