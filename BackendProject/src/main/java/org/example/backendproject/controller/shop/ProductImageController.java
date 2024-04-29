package org.example.backendproject.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.entity.ProductImage;
import org.example.backendproject.service.shop.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : ProductImageController
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
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;

//    상품 이미지 상세 조회
    @GetMapping("/productImage/{pdId}")
    public ResponseEntity<Object> findById(@PathVariable int pdId) {
        try {
//            상세 조회 서비스 실행
            Optional<ProductImage> optionalProductImage = productImageService.findById(pdId);
            if (optionalProductImage.isEmpty() == true) {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                조회 성공
                return new ResponseEntity<>(optionalProductImage.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.debug("에러" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
