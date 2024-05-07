package org.example.backend.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.shop.IQnaDto;
import org.example.backend.model.dto.shop.IReviewDto;
import org.example.backend.repository.mypage.MyQnaRepository;
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
 * packageName : org.example.backend.controller.mypage
 * fileName : MyQnaController
 * author : gumiji
 * date : 5/7/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/7/24         gumiji          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyQnaController {
    @Autowired
    MyQnaRepository myQnaRepository;

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/inquiry/{userId}")
    public ResponseEntity<Object> findAll(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<IQnaDto> qnaDtoPage
                    = myQnaRepository.findByUserId(userId, pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("inquiry", qnaDtoPage.getContent());             // 리뷰배열
            response.put("currentPage", qnaDtoPage.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", qnaDtoPage.getTotalElements()); // 전체데이터개수
            response.put("totalPages", qnaDtoPage.getTotalPages());    // 전체페이지수(x)
            if (qnaDtoPage.isEmpty() == false) {
                log.debug("데이터 : " + response);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
