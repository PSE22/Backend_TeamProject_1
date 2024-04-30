package org.example.backendproject.service.shop;

import org.example.backendproject.model.dto.shop.IProductDto;
import org.example.backendproject.model.entity.Product;
import org.example.backendproject.model.entity.ProductImage;
import org.example.backendproject.repository.shop.ProductImageRepository;
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

    @Autowired
    ProductImageRepository productImageRepository;

    /**
     * 상품 전체 조회
     * @param categoryCode
     * @param pageable
     * @return
     */
    public Page<IProductDto> findAllByCategoryCodeContaining(String categoryCode, Pageable pageable) {
        Page<IProductDto> page = productRepository.findAllByCategoryCodeContaining(categoryCode, pageable);
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

//    상품 이미지 상세 조회
    public Optional<ProductImage> findById2(int pdId) {
        Optional<ProductImage> optionalProductImage = productImageRepository.selectByPdIdContaining(pdId);
        return optionalProductImage;
    }
}
