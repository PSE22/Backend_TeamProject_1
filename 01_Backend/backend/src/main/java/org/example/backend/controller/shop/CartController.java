package org.example.backend.controller.shop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.shop.ICartDto;
import org.example.backend.model.entity.Cart;
import org.example.backend.model.entity.User;
import org.example.backend.security.jwt.JwtUtils;
import org.example.backend.service.shop.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
    @GetMapping("/cart/{userId}")
    public ResponseEntity<Object> findAll(
            @PathVariable String userId
    ) {
        try {
//            전체 조회 서비스 실행
            List<ICartDto> cart
                    = cartService.getUserCart(userId);

            if (cart.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(cart, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
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