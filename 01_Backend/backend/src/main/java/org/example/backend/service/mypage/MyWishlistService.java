package org.example.backend.service.mypage;



import org.example.backend.model.common.PdIdUserIdPk;
import org.example.backend.model.dto.mypage.WishlistDto;
import org.example.backend.repository.mypage.MyWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<WishlistDto> getUserWishlist(String userId) {
        return myWishlistRepository.findByUserId(userId);
    }

    // pdId를 사용한 소프트 삭제 함수
    @Transactional
    public void removeWishlistItem(Long pdId, String userId) {
        myWishlistRepository.deleteById(new PdIdUserIdPk(pdId, userId));
    }
    }


