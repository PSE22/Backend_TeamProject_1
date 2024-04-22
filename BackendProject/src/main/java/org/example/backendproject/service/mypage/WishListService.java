package org.example.backendproject.service.mypage;

import org.example.backendproject.model.entity.Wishlist;
import org.example.backendproject.repository.mypage.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Wishlist> findById(int pd_id) {
//        JPA 상세조회 함수 실행
        Optional<Wishlist> optionalWishlist
                = wishListRepository.findById(pd_id);
        return optionalWishlist;
    }



}
