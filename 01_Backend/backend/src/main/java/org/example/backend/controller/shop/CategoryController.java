package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.service.shop.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
