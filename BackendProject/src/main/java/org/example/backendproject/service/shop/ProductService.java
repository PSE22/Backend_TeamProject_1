package org.example.backendproject.service.shop;

import org.example.backendproject.model.dto.shop.IProductDto;
import org.example.backendproject.model.dto.shop.IProductImgDto;
import org.example.backendproject.model.entity.Product;
import org.example.backendproject.model.entity.Wishlist;
import org.example.backendproject.repository.shop.ProductImgRepository;
import org.example.backendproject.repository.shop.ProductRepository;
import org.example.backendproject.repository.shop.ProductWishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
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
    ProductImgRepository productImgRepository;

    @Autowired
    ProductWishListRepository productWishListRepository;

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

//    상품 이미지 전체 조회
    public List<IProductImgDto> findById2(int pdId) {
        List<IProductImgDto> productImgDtoList = productImgRepository.findAllByPdIdContaining(pdId);
        return productImgDtoList;
    }

//    위시 리스트에 저장
    public Wishlist save(Wishlist wishlist) {
        Wishlist wishlist2 = productWishListRepository.save(wishlist);

        return wishlist2;
    }
}
