package org.example.backend.service.mypage;

import org.example.backend.model.dto.shop.ICouponDto;
import org.example.backend.model.dto.shop.IQnaDto;
import org.example.backend.repository.mypage.MyCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyCouponService
 * author : gumiji
 * date : 5/7/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/7/24         gumiji          최초 생성
 */
@Service
public class MyCouponService {
    @Autowired
    MyCouponRepository myCouponRepository;

    public Page<ICouponDto> findByUserId(String userId,
                                         Pageable pageable) {
        Page<ICouponDto> page = myCouponRepository.findByUserId(userId, pageable);
        return page;
    }
}
