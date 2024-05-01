package org.example.backendproject.service.mypage;

import org.example.backendproject.model.dto.mypage.IMyCouponDto;
import org.example.backendproject.repository.mypage.MyCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : MyCouponService
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
public class MyCouponService {
    @Autowired
    MyCouponRepository myCouponRepository;

    //    TODO: 전체 조회 함수
    public Page<IMyCouponDto> selectByCpContaining(
            String cpName,
            Pageable pageable
    ) {
        Page<IMyCouponDto> page = myCouponRepository.selectByCouponContaining(cpName, pageable);
        return page;
    }
}
