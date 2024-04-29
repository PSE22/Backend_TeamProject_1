package org.example.backend.service.mypage;

import org.example.backend.model.dto.mypage.WishlistDto;
import org.example.backend.model.entity.Wishlist;
import org.example.backend.repository.mypage.MyWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName : org.example.backendproject.service.mypage
 * fileName : WishListService
 * author : GGG
 * date : 2024-04-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22         GGG          최초 생성
 */
@Service
public class MyWishlistService {

    @Autowired
    private MyWishlistRepository wishlistRepository;

    /**
     * 로그인한 사용자의 위시리스트를 페이지 단위로 조회합니다.
     * 이 메소드는 로그인 상태를 확인한 후, 요청된 사용자 ID에 해당하는 위시리스트 데이터를 반환합니다.
     *
     * @param userId 사용자의 고유 ID
     * @param pageable 페이지 요청 정보 (페이지 번호, 페이지 크기 등)
     * @return 조회된 위시리스트 페이지
     * @throws AccessDeniedException 사용자가 로그인하지 않은 경우 접근 거부 예외를 발생시킵니다.
     */
    @Transactional(readOnly = true)
    public Page<WishlistDto> selectWishlistContaining(String userId, Pageable pageable) {
        // 현재 인증된 사용자의 정보를 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증 객체가 null이 아니고, 사용자가 인증된 경우
        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자 ID에 해당하는 위시리스트를 페이지 단위로 조회하여 반환합니다.
            return wishlistRepository.selectWishlistContaining(userId, pageable);
        } else {
            // 인증된 사용자가 아니라면 접근 거부 예외를 발생시킵니다.
            throw new AccessDeniedException("접근 권한이 없습니다");
        }
    }
    // pdId를 사용한 소프트 삭제 함수
    @Transactional
    public void removeByPdId(Integer pdId) {
        List<Wishlist> wishlists = wishlistRepository.findByPdId(pdId);
        wishlists.forEach(wishlist -> {
            wishlist.setStatus("N");
            wishlistRepository.save(wishlist);
        });
    }
}

