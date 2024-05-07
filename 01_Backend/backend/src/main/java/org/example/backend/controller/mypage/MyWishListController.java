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
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyWishListController {

    @Autowired
    MyWishlistService myWishlistService;

    // 조회함수 페이징 X
    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@PathVariable String userId) {
        List<WishlistDto> wishlist = myWishlistService.getUserWishlist(userId);
        if (wishlist != null && !wishlist.isEmpty()) {
            return new ResponseEntity<>(wishlist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    // 삭제함수
    @DeleteMapping("/wishlist/{userId}/{pdId}")
    public ResponseEntity<?> deleteWishlistItem(@PathVariable String userId, @PathVariable Long pdId) {
        try {
            myWishlistService.removeWishlistItem(pdId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}