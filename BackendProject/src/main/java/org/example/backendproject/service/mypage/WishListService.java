package org.example.backendproject.service.mypage;

import org.example.backendproject.model.entity.Wishlist;
import org.example.backendproject.repository.mypage.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

//    전체조회
    public List<Wishlist> findAll() {
        List<Wishlist> list = wishListRepository.findAll();
        return list;
    }

//    상세조회
    public Optional<Wishlist> findById(int pd_id) {
        Optional<Wishlist> optionalWishlist
                = wishListRepository.findById(pd_id);
        return optionalWishlist;
    }


//    검색 : 페이징처리
    public Page<Wishlist> findAllByPdNameContaining(String pdName,
                                               Pageable pageable) {
        Page<Wishlist> page
                = wishListRepository
                .findAllByPdNameContaining(pdName, pageable);
        return page;
    }

//    저장 함수
    public Wishlist save(Wishlist wishlist) {
        Wishlist wishlist2 = wishListRepository.save(wishlist);

        return wishlist2;
    }

    //    삭제 함수
    public boolean removeById(int pdId) {
        if(wishListRepository.existsById(pdId) == true) {
            wishListRepository.deleteById(pdId);
            return true;
        } else {
            return false;
        }
    }


}
