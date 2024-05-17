package org.example.backend.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.admin.AdminPdReview;
import org.example.backend.service.admin.AdminPdReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminPdReviewController {

    @Autowired
    AdminPdReviewService adminPdReviewService;

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/admin-pdqna-review")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String reviewTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try{
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<AdminPdReview> pageList
                    = adminPdReviewService.findAllByAdminReviewTitleContaining(reviewTitle,pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("adminPdReview", pageList.getContent());    // 상품리뷰배열
            response.put("currentPage", pageList.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", pageList.getTotalElements()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages());    // 전체페이지수(x)

            if(pageList.isEmpty() == true) {
//                1) pageList 값이 없으면 : DB 테이블 없음 => NO_CONTENT(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 상세조회
    @GetMapping("/admin-pdqna-review/{reviewId}")
    public ResponseEntity<Object> findById(
            @PathVariable long reviewId
    ) {
        try {
//            DB 상세조회 서비스 함수 실행
            Optional<AdminPdReview> optionalAdminPdReview = adminPdReviewService.findById(reviewId);

            if(optionalAdminPdReview.isEmpty() == true) {
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalAdminPdReview.get()
                        , HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 저장 함수
    @PostMapping("/admin-pdqna-review")
    public ResponseEntity<Object> create(
            @RequestBody AdminPdReview adminPdReview
    ) {
        try {
//            DB 서비스 저장 함수 실행
            AdminPdReview adminPdReview2 = adminPdReviewService.save(adminPdReview);

            return new ResponseEntity<>(adminPdReview2, HttpStatus.OK);

        } catch (Exception e) {
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 수정함수
    @PutMapping("/admin-pdqna-review/{reviewId}")
    public ResponseEntity<Object> update(
            @PathVariable long reviewId,
            @RequestBody AdminPdReview adminPdReview
    ) {
        try {
            AdminPdReview adminPdReview2 = adminPdReviewService.save(adminPdReview);

            return new ResponseEntity<>(adminPdReview2, HttpStatus.OK);
        } catch (Exception e) {
//            DB 에러 (서버 에러) -> 500 신호(INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     TODO: 삭제 함수
    @DeleteMapping("/admin-pdqna-review/deletion/{reviewId}")
    public ResponseEntity<Object> delete(
            @PathVariable long reviewId
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = adminPdReviewService.removeById(reviewId);

            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
//            서버(DB) 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
