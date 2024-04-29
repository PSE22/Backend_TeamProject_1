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
     * 지정된 사용자의 위시리스트 페이지를 검색합니다.
     * @param userId 위시리스트를 검색할 사용자 ID입니다.
     * @param pageable 페이징 정보입니다.
     * @return 위시리스트의 페이지입니다.
     */
    @Transactional(readOnly = true)
    public Page<WishlistDto> selectWishlistContaining(String userId, Pageable pageable) {
        System.out.println("사용자 ID로 위시리스트 검색: " + userId);
        return wishlistRepository.selectWishlistContaining(userId, pageable);
    }

    /**
     * 제품 ID를 사용하여 위시리스트 항목의 소프트 삭제를 수행합니다.
     * @param pdId 삭제할 위시리스트 항목의 제품 ID입니다.
     */
    @Transactional
    public void removeByPdId(Integer pdId) {
        List<Wishlist> wishlists = wishlistRepository.findByPdId(pdId);
        for (Wishlist wishlist : wishlists) {
            wishlist.setStatus("N");
            wishlistRepository.save(wishlist);
            System.out.println("소프트 삭제 수행: 제품 ID " + pdId);
        }
    }
}
