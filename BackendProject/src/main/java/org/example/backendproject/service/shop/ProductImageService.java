package org.example.backendproject.service.shop;

import org.example.backendproject.model.entity.ProductImage;
import org.example.backendproject.repository.shop.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : ProductImageService
 * author : sjuk2
 * date : 2024-04-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         sjuk2          최초 생성
 */
@Service
public class ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository;

    public Optional<ProductImage> findById(int pdId) {
        Optional<ProductImage> optionalProductImage = productImageRepository.findById(pdId);
        return optionalProductImage;
    }
}
