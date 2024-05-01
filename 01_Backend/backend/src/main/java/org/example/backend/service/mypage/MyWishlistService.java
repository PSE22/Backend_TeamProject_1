package org.example.backend.service.mypage;

import org.example.backend.model.common.PdIdUserIdPk;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    MyWishlistRepository myWishlistRepository;


    //    전체조회
    public Page<WishlistDto> selectWishlistContaining(
            String userId,
            Pageable pageable
    ) {
        Page<WishlistDto> page = myWishlistRepository.selectWishlistContaining(userId, pageable);
        return page;
    }


    // pdId를 사용한 소프트 삭제 함수
    @Transactional
    public void removeByPdId(Integer pdId) {
        // 주어진 pdId로 모든 관련 위시리스트 항목을 찾음
        List<Wishlist> wishlists = myWishlistRepository.findByPdId(pdId);

        // 검색된 모든 위시리스트 항목을 반복 처리
        for (Wishlist wishlist : wishlists) {
            // 각 위시리스트 항목의 상태를 'N'으로 설정하여 소프트 삭제를 표시
            wishlist.setStatus("N");

            myWishlistRepository.save(wishlist);
        }
    }
}

