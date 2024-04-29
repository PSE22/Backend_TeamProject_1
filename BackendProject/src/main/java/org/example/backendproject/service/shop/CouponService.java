package org.example.backendproject.service.shop;

import org.example.backendproject.model.dto.mypage.ICouponDto;
import org.example.backendproject.repository.shop.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : CouponService
 * author : gumiji
 * date : 4/25/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/25/24         gumiji          최초 생성
 */
@Service
public class CouponService {
    @Autowired
    CouponRepository couponRepository;

    //    TODO: 전체 조회 함수
    public Page<ICouponDto> selectByCpContaining(
            String cpName,
            Pageable pageable
    ) {
        Page<ICouponDto> page = couponRepository.selectByCouponContaining(cpName, pageable);
        return page;
    }
}
