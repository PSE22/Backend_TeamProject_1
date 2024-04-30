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
    MyWishlistService myWishlistService; // 위시리스트 서비스를 자동 주입

    // 전체 조회 함수 + 검색 + 페이징
    @GetMapping("/wishlist")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") int page, // 페이지 번호 기본값 0
            @RequestParam(defaultValue = "3") int size, // 페이지 크기 기본값 3
            Authentication authentication // 스프링 시큐리티의 인증 객체
    )
    {
        log.debug("전체 위시리스트 조회를 시작합니다.");
        try {
            String userId = authentication.getName(); // 현재 로그인한 사용자의 ID 가져오기
            log.debug("로그인한 사용자 ID: {}", userId);
            Pageable pageable = PageRequest.of(page, size); // 페이지 요청 생성

            // 전체 조회 서비스 함수 실행
            Page<WishlistDto> wishDtoPage = myWishlistService.selectWishlistContaining(userId, pageable);
            log.debug("조회된 페이지 정보: 페이지 번호 = {}, 페이지 크기 = {}, 총 요소 수 = {}", wishDtoPage.getNumber(), wishDtoPage.getSize(), wishDtoPage.getTotalElements());

            Map<String, Object> response = new HashMap<>();
            response.put("wishlist", wishDtoPage.getContent()); // 위시리스트 항목
            response.put("currentPage", wishDtoPage.getNumber()); // 현재 페이지 번호
            response.put("totalItems", wishDtoPage.getTotalElements()); // 전체 아이템 수
            response.put("totalPages", wishDtoPage.getTotalPages()); // 전체 페이지 수

            if (wishDtoPage.isEmpty()) {
                log.debug("위시리스트 항목이 비어 있습니다.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 내용이 없을 경우 204 상태 코드 반환
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK); // 성공적으로 조회된 경우 응답 데이터와 함께 200 상태 코드 반환
            }

        } catch (Exception e) {
            log.error("위시리스트 조회 중 오류 발생", e); // 오류 로깅
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 서버 내부 오류 시 500 상태 코드 반환
        }
    }

    // pdId를 사용하여 Wishlist 항목 삭제
    @DeleteMapping("/wishlist/deletion/{pdId}")
    public ResponseEntity<Object> deleteByPdId(@PathVariable Integer pdId) {
        myWishlistService.removeByPdId(pdId); // pdId에 해당하는 위시리스트 항목을 삭제
        return new ResponseEntity<>(HttpStatus.OK); // 성공적으로 삭제되면 200 상태 코드 반환
    }
}