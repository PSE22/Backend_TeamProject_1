package org.example.backendproject.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.common.PdIdUserIdPk;
import org.example.backendproject.model.dto.mypage.WishlistDto;
import org.example.backendproject.service.mypage.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyWishListController {

    @Autowired
    WishlistService wishlistService;

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/wishlist")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") Integer pdId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    )
    {
        try{
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<WishlistDto> wishDtoPage
                    = wishlistService.  selectWishlistContaining(pdId,pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("wishlist", wishDtoPage.getContent());             // 부서배열
            response.put("currentPage", wishDtoPage.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", wishDtoPage.getTotalElements()); // 전체데이터개수
            response.put("totalPages", wishDtoPage.getTotalPages());    // 전체페이지수(x)

            if(wishDtoPage.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.debug("에러 : "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // pdId를 사용하여 Wishlist 항목 삭제
    @DeleteMapping("/wishlist/deletion/{pdId}")
    public ResponseEntity<Object> deleteByPdId(@PathVariable Integer pdId) {
        wishlistService.removeByPdId(pdId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}