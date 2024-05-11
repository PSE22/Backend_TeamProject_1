package org.example.backend.service.mypage;

import org.example.backend.model.dto.mypage.IMyOrderDetailDto;
import org.example.backend.model.dto.shop.ICartDto;
import org.example.backend.repository.mypage.MyOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyOrderDetailService
 * author : gumiji
 * date : 5/10/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/10/24         gumiji          최초 생성
 */
@Service
public class MyOrderDetailService {

    @Autowired
    MyOrderDetailRepository myOrderDetailRepository;

    public Optional<IMyOrderDetailDto> findByOrderId(Long orderId) {
//        DB 상세조회 실행
        Optional<IMyOrderDetailDto> optionalIMyOrderDetail
                = myOrderDetailRepository.findByOrderId(orderId);
        return optionalIMyOrderDetail;
    }

    public List<IMyOrderDetailDto> findByOrderList(Long orderId) {
//        DB 상세조회 실행
        List<IMyOrderDetailDto> findByOrderList
                = myOrderDetailRepository.findByOrderList(orderId);
        return findByOrderList;
    }
}