package org.example.backend.service.shop;

import org.example.backend.model.common.CpIdUserIdPk;
import org.example.backend.model.common.PdIdUserIdPk;
import org.example.backend.model.dto.shop.IBestProductDto;
import org.example.backend.model.dto.shop.IProductDto;
import org.example.backend.model.dto.shop.IProductImgDto;
import org.example.backend.model.entity.Cart;
import org.example.backend.model.entity.Product;
import org.example.backend.model.entity.UserCoupon;
import org.example.backend.model.entity.Wishlist;
import org.example.backend.repository.shop.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserCouponRepository userCouponRepository;

    //    모든 상품 조회(검색)
    public Page<Product> findAllByPdNameContaining(String pdName,
                                                   Pageable pageable) {
//        DB like 검색 함수 실행 : 페이징 처리
        Page<Product> page =
                productRepository
                        .findAllByPdNameContaining(pdName, pageable);
        return page;
    }

    //    카테고리별 전체 상품 조회
    public Page<Product> findByCategoryAll(String categoryCode, Pageable pageable) {
        Page<Product> page = productRepository.findByCategoryAll(categoryCode, pageable);
        return page;
    }

    //     카테고리별 전체 상품 정렬 (신상품순)
    public Page<Product> findAllByOrderByNewProduct(String categoryCode, Pageable pageable) {
        Page<Product> page = productRepository.findAllByOrderByNewProduct(categoryCode, pageable);
        return page;
    }

    //     카테고리별 전체 상품 정렬 (인기순)
    public Page<IProductDto> findAllByOrderByOrderDetailCnt(String categoryCode, Pageable pageable) {
        Page<IProductDto> page = productRepository.findAllByOrderByOrderDetailCnt(categoryCode, pageable);
        return page;
    }

//     카테고리별 전체 상품 정렬 (낮은 가격순)
public Page<Product> findAllByOrderByPdPriceAsc(String categoryCode, Pageable pageable) {
    Page<Product> page = productRepository.findAllByOrderByPdPriceAsc(categoryCode, pageable);
    return page;
}

//     카테고리별 전체 상품 정렬 (높은 가격순)
public Page<Product> findAllByOrderByPdPriceDesc(String categoryCode, Pageable pageable) {
    Page<Product> page = productRepository.findAllByOrderByPdPriceDesc(categoryCode, pageable);
    return page;
}

    //    베스트 상품 3개 조회
    public List<IBestProductDto> findThreeBestProduct() {
        List<IBestProductDto> list = productRepository.findThreeBestProductOrderByAddDate();
        return list;
    }

    //    베스트 상품 전체 조회(일간 판매량 높은순)
    public List<IBestProductDto> findAllBestProductDay() {
        List<IBestProductDto> list = productRepository.findAllBestProductOrderByCountDay();
        return list;
    }

    //    베스트 상품 전체 조회(월간 판매량 높은순)
    public List<IBestProductDto> findAllBestProductMonth() {
        List<IBestProductDto> list = productRepository.findAllBestProductOrderByCountMonth();
        return list;
    }

    //    베스트 상품 전체 조회(연간 판매량 높은순)
    public List<IBestProductDto> findAllBestProductYear() {
        List<IBestProductDto> list = productRepository.findAllBestProductOrderByCountYear();
        return list;
    }

    //    신상품 전체 조회
    public List<Product> findAllNewProduct() {
        List<Product> list = productRepository.findAllNewProductOrderByAddDate();
        return list;
    }

    //    신상품 전체 조회(높은 가격순)
    public List<Product> findAllNewProductHigh() {
        List<Product> list = productRepository.findAllNewProductOrderByAddDateAndPriceDesc();
        return list;
    }

    //    신상품 전체 조회(낮은 가격순)
    public List<Product> findAllNewProductLow() {
        List<Product> list = productRepository.findAllNewProductOrderByAddDateAndPrice();
        return list;
    }

    /**
     * 상품 상세 조회
     *
     * @param pdId
     * @return
     */
    public Optional<Product> findById(long pdId) {
        Optional<Product> optionalProduct = productRepository.findById(pdId);
        return optionalProduct;
    }

    //    상품 이미지 전체 조회
    public List<IProductImgDto> findById2(long pdId) {
        List<IProductImgDto> productImgDtoList = productImgRepository.findAllByPdIdContaining(pdId);
        return productImgDtoList;
    }

    //    위시 리스트 조회
    public Integer findBypPdIdAndUserId(Long pdId, String userId) {
        Integer wishListNum = productWishListRepository.findBypPdIdAndUserId(pdId, userId);
        return wishListNum;
    }

    //    위시 리스트에 저장/수정
    public Wishlist save(Wishlist wishlist) {
        Wishlist wishlist2 = productWishListRepository.save(wishlist);

        return wishlist2;
    }

    //    위시 리스트에서 삭제
    public boolean removeById(PdIdUserIdPk pdIdUserIdPk) {
        if (productWishListRepository.existsById(pdIdUserIdPk)) {
            productWishListRepository.deleteById(pdIdUserIdPk); // DB 삭제
            return true;
        } else {
            return false;
        }
    }

    //    장바구니에 저장
    public Cart save(Cart cart) {
        Cart cart2 = cartRepository.save(cart);

        return cart2;
    }

    //    쿠폰 저장
    public boolean insert(UserCoupon userCoupon) {
        CpIdUserIdPk cpIdUserIdPk = new CpIdUserIdPk(userCoupon.getCpId(), userCoupon.getUserId());
        if (userCouponRepository.existsById(cpIdUserIdPk) == false) {
            userCouponRepository.save(userCoupon);
            return true;
        } else {
            return false;
        }
    }

//    public UserCoupon update(UserCoupon userCoupon) {
//        UserCoupon userCoupon2 = userCouponRepository.save(userCoupon);
//
//        return userCoupon2;
//    }
}
