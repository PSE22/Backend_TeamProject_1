package org.example.backendproject.service.shop;

import org.example.backendproject.model.entity.Product;
import org.example.backendproject.repository.shop.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : ProductService
 * author : SAMSUNG
 * date : 2024-04-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22         SAMSUNG          최초 생성
 */
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    /**
     * 상품 전체 조회
     * @param pdName
     * @param pageable
     * @return
     */
    public Page<Product> findAllByPdIdContaining(String pdName, Pageable pageable) {
        Page<Product> page = productRepository.findAllByPdIdContaining(pdName, pageable);
        return page;
    }

    /**
     * 상품 상세 조회
     * @param pdId
     * @return
     */
    public Optional<Product> findById(int pdId) {
        Optional<Product> optionalProduct = productRepository.findById(pdId);
        return optionalProduct;
    }
}
