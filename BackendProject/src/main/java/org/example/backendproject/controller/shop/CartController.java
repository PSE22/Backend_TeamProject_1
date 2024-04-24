package org.example.backendproject.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.dto.shop.ICartDto;
import org.example.backendproject.model.entity.Cart;
import org.example.backendproject.service.shop.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : CartController
 * author : gumiji
 * date : 4/23/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/23/24         gumiji          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class CartController {
    @Autowired
    CartService cartService;

    //    TODO: 저장함수
//    저장(insert) -> post 방식 -> @PostMapping
    @PostMapping("/cart")
    public ResponseEntity<Object> create(
            @RequestBody Cart cart
    ) {
        try {
//            저장 서비스 실행
            Cart cart2 = cartService.save(cart);
            return new ResponseEntity<>(cart2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 전체조회 함수(조인) + like 검색
//    조회(select) -> get 방식 -> GetMapping
    @GetMapping("/cart")
    public ResponseEntity<Object> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer cartId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<ICartDto> cartDtoPage
                    = cartService.selectByCartContaining(cartId, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("cart", cartDtoPage.getContent()); // 배열
            response.put("currentPage", cartDtoPage.getNumber()); // 현재페이지번호
            response.put("totalItems", cartDtoPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", cartDtoPage.getTotalPages()); // 총페이지수

            if (cartDtoPage.isEmpty() == false) {
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

    //    TODO: 삭제함수
    @DeleteMapping("/cart/deletion/{cartId}")
    public ResponseEntity<Object> delete(
            @PathVariable int cartId
    ) {
        try {
//        DB 삭제 서비스 함수 실행
            boolean success = cartService.removeById(cartId);
            if (success == true) {
//                삭제 성공
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
//                삭제 실패 : 삭제할 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
