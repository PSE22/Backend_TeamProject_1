package org.example.backendproject.service.mypage;

import org.apache.kafka.common.protocol.types.Field;
import org.example.backendproject.model.common.PdIdUserIdPk;
import org.example.backendproject.model.dto.mypage.WishlistDto;
import org.example.backendproject.model.entity.Product;
import org.example.backendproject.model.entity.Wishlist;

import org.example.backendproject.repository.mypage.WishlistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;


//    전체조회
public Page<WishlistDto> selectWishlistContaining(
        Integer pdId,
        Pageable pageable
) {
    Page<WishlistDto> page = wishlistRepository.selectWishlistContaining(pdId, pageable);
    return page;
}


    // pdId를 사용한 소프트 삭제 함수
    @Transactional
    public void removeByPdId(Integer pdId) {
        // 주어진 pdId로 모든 관련 위시리스트 항목을 찾음
        List<Wishlist> wishlists = wishlistRepository.findByPdId(pdId);

        // 검색된 모든 위시리스트 항목을 반복 처리
        for (Wishlist wishlist : wishlists) {
            // 각 위시리스트 항목의 상태를 'N'으로 설정하여 소프트 삭제를 표시
            wishlist.setStatus("N");

            // 변경된 상태를 데이터베이스에 저장
            wishlistRepository.save(wishlist);
        }
        // 이 메서드의 실행이 성공적으로 완료되면 트랜잭션이 커밋되며,
        // 오류가 발생하면 자동으로 롤백됩니다.
    }
}

