package org.example.backend.service.mypage;

import org.example.backend.model.dto.mypage.IMyOrderDetailDto;
import org.example.backend.model.dto.shop.ICartDto;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.Refund;
import org.example.backend.repository.CmCodeRepository;
import org.example.backend.repository.mypage.MyOrderDetailRepository;
import org.example.backend.repository.order.RefundRepository;
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

    //    TODO: 배송지
    public Optional<IMyOrderDetailDto> findByOrderId(Long orderId) {
//        DB 상세조회 실행
        Optional<IMyOrderDetailDto> optionalIMyOrderDetail
                = myOrderDetailRepository.findByOrderId(orderId);
        return optionalIMyOrderDetail;
    }

    //    TODO: 주문 상품리스트
    public List<IMyOrderDetailDto> findByOrderList(Long orderId) {
//        DB 상세조회 실행
        List<IMyOrderDetailDto> findByOrderList
                = myOrderDetailRepository.findByOrderList(orderId);
        return findByOrderList;
    }

    //    주문 금액
    public Optional<IMyOrderDetailDto> findByOrderPrice(Long orderId) {
//        DB 상세조회 실행
        Optional<IMyOrderDetailDto> findByOrderPrice
                = myOrderDetailRepository.findByOrderPrice(orderId);
        return findByOrderPrice;
    }

    @Autowired
    RefundRepository refundRepository;
    public Refund save(Refund refund) {
//        JPA 저장 함수 : return 값 : 저장된 객체
        Refund refund2 = refundRepository.save(refund);
        return refund2;
    }
}