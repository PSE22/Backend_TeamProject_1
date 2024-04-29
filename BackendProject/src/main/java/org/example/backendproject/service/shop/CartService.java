package org.example.backendproject.service.shop;

import org.example.backendproject.model.dto.shop.ICartDto;
import org.example.backendproject.model.entity.Cart;
import org.example.backendproject.repository.shop.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : CartService
 * author : gumiji
 * date : 4/23/24
 * description : 장바구니 서비스
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/23/24         gumiji          최초 생성
 */
@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    //    TODO: 저장(수정)함수
    public Cart save(Cart cart) {
//        DB 저장 함수 실행
        Cart cart2 = cartRepository.save(cart);
        return cart2;
    }

    public Page<ICartDto> selectByCartContaining(
            String userId,
            Pageable pageable
    ) {
        Page<ICartDto> page = cartRepository.selectByCartContaining(userId, pageable);
        return page;
    }

    //    TODO: 삭제 함수
    public boolean removeById(int cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);  // DB 삭제
            return true;
        } else {
            return false;
        }
    }
}
