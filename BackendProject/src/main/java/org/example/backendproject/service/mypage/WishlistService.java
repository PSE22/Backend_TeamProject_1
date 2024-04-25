package org.example.backendproject.service.mypage;

import org.apache.kafka.common.protocol.types.Field;
import org.example.backendproject.model.dto.mypage.WishlistDto;
import org.example.backendproject.model.entity.Product;
import org.example.backendproject.model.entity.Wishlist;

import org.example.backendproject.repository.mypage.WishlistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

//
//    //    삭제 함수
//    public boolean removeById(int pdId) {
//        if(wishlistRepository.existsById(pdId) == true) {
//            wishlistRepository.deleteById(pdId);
//            return true;
//        } else {
//            return false;
//        }
//    }

//    //    TODO: 상세조회
//    public Optional<Product> findById(int pdId) {
////        DB 상세조회 실행
//        Optional<Product> optionalProduct
//                = productRepository.findById(pdId);
//        return optionalProduct;
//    }
}
