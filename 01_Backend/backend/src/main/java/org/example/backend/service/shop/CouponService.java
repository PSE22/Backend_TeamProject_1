package org.example.backend.service.shop;

import org.example.backend.model.dto.shop.ICouponDto;
import org.example.backend.repository.shop.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : CouponService
 * author : sjuk2
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         sjuk2          최초 생성
 */
@Service
public class CouponService {
    @Autowired
    CouponRepository couponRepository;

    public List<ICouponDto> findById(int pdId) {
        List<ICouponDto> couponDtoList = couponRepository.findAllByPdidContaining(pdId);
        return couponDtoList;
    }
}
