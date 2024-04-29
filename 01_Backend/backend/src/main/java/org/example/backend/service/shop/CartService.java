package org.example.backend.service.shop;

import org.example.backend.model.dto.shop.ICartDto;
import org.example.backend.model.entity.Cart;
import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.repository.shop.CartRepository;
import org.example.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    //    TODO: 저장(수정)함수
    public Cart save(Cart cart) {
//        DB 저장 함수 실행
        Cart cart2 = cartRepository.save(cart);
        return cart2;
    }




    public List<ICartDto> getUserCart(String token) {
        String userId = jwtUtils.getUserIdFromJwtToken(token);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return cartRepository.findByUserId(userId);
        } else {
            // 사용자가 존재하지 않을 경우에 대한 처리
            return Collections.emptyList();
        }
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
