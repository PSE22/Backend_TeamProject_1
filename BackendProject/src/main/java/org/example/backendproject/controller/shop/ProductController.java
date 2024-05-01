package org.example.backendproject.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.dto.shop.IProductDto;
import org.example.backendproject.model.dto.shop.IProductImgDto;
import org.example.backendproject.model.entity.Product;
import org.example.backendproject.model.entity.Wishlist;
import org.example.backendproject.service.shop.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : ProductController
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
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class ProductController {
    @Autowired
    ProductService productService;

    // 상품 전체 조회
    @GetMapping("/category")
    public ResponseEntity<Object> findAllByCategoryCodeContaining(
                                        @RequestParam(defaultValue = "") String categoryCode,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "4") int size) {
        try {
            // 페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

            // 전체 조회 서비스 실행
            Page<IProductDto> productPage = productService.findAllByCategoryCodeContaining(categoryCode, pageable);

            // 공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("product", productPage.getContent());          // product 배열
            response.put("currentPage", productPage.getNumber());       // 현재 페이지 번호
            response.put("totalItems", productPage.getTotalElements()); // 전체 데이터 수
            response.put("totalPages", productPage.getTotalPages());    // 전체 페이지 수

            if(productPage.isEmpty() == false) {
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

    // 상품 상세 조회 함수
    @GetMapping("/product/{pdId}")
    public ResponseEntity<Object> findById(@PathVariable int pdId) {
        try {
            // 상세 조회 서비스 실행
            Optional<Product> optionalProduct = productService.findById(pdId);
            if (optionalProduct.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 상품 이미지 전체 조회 함수
    @GetMapping("/productImage/{pdId}")
    public ResponseEntity<Object> findById2(@PathVariable int pdId) {
        try {
            List<IProductImgDto> productImgDtoList = productService.findById2(pdId);
            if (productImgDtoList.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(productImgDtoList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 저장 함수
    @PostMapping("/product/wishList")
    public ResponseEntity<Object> create(
            @RequestBody Wishlist wishlist
    ) {
        try {
//            DB 서비스 저장 함수 실행
            Wishlist wishlist2 = productService.save(wishlist);
//            성공(OK) 메세지 + 저장된 객체(wishList2)
            return new ResponseEntity<>(wishlist2, HttpStatus.OK);
        } catch (Exception e) {
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
