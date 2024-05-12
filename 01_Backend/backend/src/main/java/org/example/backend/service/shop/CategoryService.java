package org.example.backend.service.shop;

import org.example.backend.model.entity.Product;
import org.example.backend.repository.shop.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backend.service.shop
 * fileName : CategoryService
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
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * 상품 전체 조회 함수 (카테고리별)
     * @param categoryCode
     * @param pageable
     * @return
     */
    public Page<Product> findByCategoryAll(String categoryCode, Pageable pageable) {
        Page<Product> page = categoryRepository.findByCategoryAll(categoryCode, pageable);
        return page;
    }
}
