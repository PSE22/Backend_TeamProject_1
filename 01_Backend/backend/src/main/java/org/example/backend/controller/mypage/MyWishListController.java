package org.example.backend.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.mypage.WishlistDto;
import org.example.backend.service.mypage.MyWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyWishListController {

    @Autowired
    MyWishlistService myWishlistService;

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<Object> findAll(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<WishlistDto> wishDtoPage
                    = myWishlistService.selectWishlistContaining(userId, pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("wishlist", wishDtoPage.getContent());             // 부서배열
            response.put("currentPage", wishDtoPage.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", wishDtoPage.getTotalElements()); // 전체데이터개수
            response.put("totalPages", wishDtoPage.getTotalPages());    // 전체페이지수(x)

            if (wishDtoPage.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.debug("에러 : " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletion/{pdId}/{userId}")
    public ResponseEntity<?> removeItem(@PathVariable Integer pdId,
                                        @PathVariable String userId) {
        myWishlistService.removeWishlistItem(pdId, userId);
        return ResponseEntity.ok().build();
    }
}