package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.common.PdIdUserIdPk;
import org.example.backend.model.dto.shop.IBestProductDto;
import org.example.backend.model.dto.shop.IProductDto;
import org.example.backend.model.dto.shop.IProductImgDto;
import org.example.backend.model.entity.Cart;
import org.example.backend.model.entity.Product;
import org.example.backend.model.entity.UserCoupon;
import org.example.backend.model.entity.Wishlist;
import org.example.backend.service.shop.ProductService;
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

    //    전체 조회 함수 + like 검색
    @GetMapping("/product/search")
    public ResponseEntity<Object> findAllByPdNameContaining(
            @RequestParam(defaultValue = "") String pdName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<Product> productPage
                    = productService.findAllByPdNameContaining(pdName, pageable);

//            공통 페이징 객체 생성 : 자료 구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("product", productPage.getContent()); // product 배열
            response.put("currentPage", productPage.getNumber()); // 현재페이지번호
            response.put("totalItems", productPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", productPage.getTotalPages()); // 총페이지수

            if (productPage.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    카테고리별 전체 상품 조회
    @GetMapping("/category")
    public ResponseEntity<Object> findByCategoryAll(@RequestParam(defaultValue = "") String categoryCode,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {
        try {
            // 페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> categoryPage = productService.findByCategoryAll(categoryCode, pageable);
            // 공통 페이징 객체 생성
            Map<String, Object> response = new HashMap<>();
            response.put("product", categoryPage.getContent()); // product 배열
            response.put("currentPage", categoryPage.getNumber()); // 현재페이지번호
            response.put("totalItems", categoryPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", categoryPage.getTotalPages()); // 총페이지수

            if (categoryPage.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     카테고리별 전체 상품 정렬 (신상품순)
    @GetMapping("/category/order/new-product")
    public ResponseEntity<Object> findAllByOrderByNewProduct(@RequestParam(defaultValue = "") String categoryCode,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size) {
        try {
            // 페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> categoryPage = productService.findAllByOrderByNewProduct(categoryCode, pageable);
            // 공통 페이징 객체 생성
            Map<String, Object> response = new HashMap<>();
            response.put("product", categoryPage.getContent()); // product 배열
            response.put("currentPage", categoryPage.getNumber()); // 현재페이지번호
            response.put("totalItems", categoryPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", categoryPage.getTotalPages()); // 총페이지수

            if (categoryPage.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     카테고리별 전체 상품 정렬 (인기순)
    @GetMapping("/category/order/detail-cnt")
    public ResponseEntity<Object> findAllByOrderByOrderDetailCnt(@RequestParam(defaultValue = "") String categoryCode,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "3") int size) {
        try {
            // 페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);
            Page<IProductDto> categoryPage = productService.findAllByOrderByOrderDetailCnt(categoryCode, pageable);
            // 공통 페이징 객체 생성
            Map<String, Object> response = new HashMap<>();
            response.put("product", categoryPage.getContent()); // product 배열
            response.put("currentPage", categoryPage.getNumber()); // 현재페이지번호
            response.put("totalItems", categoryPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", categoryPage.getTotalPages()); // 총페이지수

            if (categoryPage.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     카테고리별 전체 상품 정렬 (낮은 가격순)
    @GetMapping("/category/order/price-asc")
    public ResponseEntity<Object> findAllByOrderByPdPriceAsc(@RequestParam(defaultValue = "") String categoryCode,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size) {
        try {
            // 페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> categoryPage = productService.findAllByOrderByPdPriceAsc(categoryCode, pageable);
            // 공통 페이징 객체 생성
            Map<String, Object> response = new HashMap<>();
            response.put("product", categoryPage.getContent()); // product 배열
            response.put("currentPage", categoryPage.getNumber()); // 현재페이지번호
            response.put("totalItems", categoryPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", categoryPage.getTotalPages()); // 총페이지수

            if (categoryPage.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //     카테고리별 전체 상품 정렬 (높은 가격순)
    @GetMapping("/category/order/price-desc")
    public ResponseEntity<Object> findAllByOrderByPdPriceDesc(@RequestParam(defaultValue = "") String categoryCode,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size) {
        try {
            // 페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> categoryPage = productService.findAllByOrderByPdPriceDesc(categoryCode, pageable);
            // 공통 페이징 객체 생성
            Map<String, Object> response = new HashMap<>();
            response.put("product", categoryPage.getContent()); // product 배열
            response.put("currentPage", categoryPage.getNumber()); // 현재페이지번호
            response.put("totalItems", categoryPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", categoryPage.getTotalPages()); // 총페이지수

            if (categoryPage.isEmpty() == false) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    베스트 상품 3개 조회
    @GetMapping("/home/best3/product")
    public ResponseEntity<Object> findThreeBestProduct() {
        try {
            List<IBestProductDto> list = productService.findThreeBestProduct();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    베스트 상품 전체 조회(일간 판매량 높은순)
    @GetMapping("/home/best/product/day")
    public ResponseEntity<Object> findAllBestProductDay() {
        try {
            List<IBestProductDto> list = productService.findAllBestProductDay();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    베스트 상품 전체 조회(월간 판매량 높은순)
    @GetMapping("/home/best/product/month")
    public ResponseEntity<Object> findAllBestProductMonth() {
        try {
            List<IBestProductDto> list = productService.findAllBestProductMonth();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    베스트 상품 전체 조회(연간 판매량 높은순)
    @GetMapping("/home/best/product/year")
    public ResponseEntity<Object> findAllBestProductYear() {
        try {
            List<IBestProductDto> list = productService.findAllBestProductYear();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    신상품 전체 조회
    @GetMapping("/home/new/product")
    public ResponseEntity<Object> findAllNewProduct() {
        try {
            List<Product> list = productService.findAllNewProduct();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    신상품 전체 조회(높은 가격순)
    @GetMapping("/home/new/product/high")
    public ResponseEntity<Object> findAllNewProductHigh() {
        try {
            List<Product> list = productService.findAllNewProductHigh();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    신상품 전체 조회(낮은 가격순)
    @GetMapping("/home/new/product/low")
    public ResponseEntity<Object> findAllNewProductLow() {
        try {
            List<Product> list = productService.findAllNewProductLow();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
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
    public ResponseEntity<Object> findById2(@PathVariable long pdId) {
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

    //    위시 리스트 조회
    @GetMapping("/product/wishList/{pdId}/{userId}")
    public ResponseEntity<Object> findBypPdIdAndUserId(
            @PathVariable Long pdId,
            @PathVariable String userId
    ) {
        try {
            Integer wishListNum = productService.findBypPdIdAndUserId(pdId, userId);
            return new ResponseEntity<>(wishListNum, HttpStatus.OK);

        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    위시 리스트에 저장
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

    //    위시 리스트에서 삭제
    @DeleteMapping("/product/deletion/{pdId}/{userId}")
    public ResponseEntity<Object> delete(
            @PathVariable Long pdId,
            @PathVariable String userId
    ) {
        try {
            PdIdUserIdPk pdIdUserIdPk = new PdIdUserIdPk(pdId, userId); // 복합키 클래스에 값을 넣기
//            DB 삭제 서비스 함수 실행
            boolean success = productService.removeById(pdIdUserIdPk);
            if (success == true) {
//                삭제 성공
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
//                삭제 실패 : 삭제할 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    장바구니에 저장
    @PostMapping("/product/cart")
    public ResponseEntity<Object> create(
            @RequestBody Cart cart
    ) {
        try {
//            DB 서비스 저장 함수 실행
            Cart cart2 = productService.save(cart);
            return new ResponseEntity<>(cart2, HttpStatus.OK);
        } catch (Exception e) {
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    쿠폰 저장
    @PostMapping("/product/coupon")
    public ResponseEntity<Object> create(
            @RequestBody UserCoupon userCoupon
    ) {
        try {
//            DB 서비스 저장 함수 실행
            boolean success = productService.insert(userCoupon);
            if (success == true) {
                return new ResponseEntity<>("쿠폰을 받았습니다", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("이미 받은 쿠폰입니다", HttpStatus.OK);
            }
        } catch (Exception e) {
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
