package org.example.backendproject.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.entity.Wishlist;
import org.example.backendproject.service.mypage.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/wishList")
public class WishListController {

    @Autowired
    WishListService wishListService;

    //    TODO: 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/wishList")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String pdName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    )
    {
        try{
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 함수 실행
            Page<Wishlist> pageList
                    = wishListService.findAllByPdNameContaining(pdName,pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("wishList", pageList.getContent());             // 부서배열
            response.put("currentPage", pageList.getNumber());       // 현재페이지 번호(x)
            response.put("totalItems", pageList.getTotalElements()); // 전체데이터개수
            response.put("totalPages", pageList.getTotalPages());    // 전체페이지수(x)

            if(pageList.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO: 상세조회 함수
    @GetMapping("/wishList/{pdId}")
    public ResponseEntity<Object> findById(
            @PathVariable int pdId
    ) {
        try {
//            DB 상세조회 서비스 함수 실행
            Optional<Wishlist> optionalWishlist = wishListService.findById(pdId);

            if(optionalWishlist.isEmpty() == true){
//                데이터 없음(203)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                데이터 있음(200)
                return new ResponseEntity<>(optionalWishlist.get(),HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//   TODO : 저장함수
    @PostMapping("/wishList")
    public ResponseEntity<Object> create(
            @RequestBody Wishlist wishlist
    ) {
        try {
//            DB 서비스 저장 함수 실행
            Wishlist wishlist2 = wishListService.save(wishlist);

            return new ResponseEntity<>(wishlist2, HttpStatus.OK);

        } catch (Exception e){
//            500 전송
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 삭제 함수
    @DeleteMapping("wishList/deletion/{pdId}")
    public ResponseEntity<Object> delete(
            @PathVariable int pdId
    ) {
        try {
            boolean success = wishListService.removeById(pdId);

            if(success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
