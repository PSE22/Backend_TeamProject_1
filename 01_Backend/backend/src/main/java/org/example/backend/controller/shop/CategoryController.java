package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.entity.Product;
import org.example.backend.service.shop.CategoryService;
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
 * packageName : org.example.backend.controller.shop
 * fileName : CategoryController
 * author : SAMSUNG
 * date : 2024-05-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10         SAMSUNG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    // 상품 전체 조회
    @GetMapping("/category")
    public ResponseEntity<Object> findByCategoryAll(@RequestParam(defaultValue = "") String categoryCode,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {
        try {
            // 페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);
            // 전체 조회 서비스 실행
            Page<Product> categoryPage = categoryService.findByCategoryAll(categoryCode, pageable);
            // 공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("product", categoryPage.getContent()); // product 배열
            response.put("currentPage", categoryPage.getNumber()); // 현재페이지번호
            response.put("totalItems", categoryPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", categoryPage.getTotalPages()); // 총페이지수
            log.debug("에러33: ", response);

            if(categoryPage.isEmpty() == false) {
                // 조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
