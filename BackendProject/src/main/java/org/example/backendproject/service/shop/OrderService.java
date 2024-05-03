package org.example.backendproject.service.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.dto.shop.IUserCouponDto;
import org.example.backendproject.model.entity.ShipAddress;
import org.example.backendproject.model.entity.User;
import org.example.backendproject.model.entity.UserCoupon;
import org.example.backendproject.repository.shop.CouponRepository;
import org.example.backendproject.repository.shop.ShipAddressRepository;
import org.example.backendproject.repository.shop.UserCouponRepository;
import org.example.backendproject.repository.shop.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : OrderService
 * author : SAMSUNG
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         SAMSUNG          최초 생성
 */
@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShipAddressRepository shipAddressRepository;

    @Autowired
    UserCouponRepository userCouponRepository;

    /**
     * 주문자(User) 정보 상세 조회
     * @param userId
     * @return
     */
    public Optional<User> findById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser;
    }

    /**
     * 배송지 정보 상세 조회
     * @param userId
     * @return
     */
    public Optional<ShipAddress> findByUserId (String userId) {
        Optional<ShipAddress> optionalShipAddress = shipAddressRepository.findByUserId(userId);
        return optionalShipAddress;
    }

    /**
     * 쿠폰 정보 상세 조회
     * @param userId
     * @return
     */
    public List<IUserCouponDto> findAllByUserCoupon(String userId) {
        List<IUserCouponDto> list = userCouponRepository.findAllByUserCoupon(userId);
        return list;
    }
}
