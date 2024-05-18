package org.example.backend.service.mypage;

import jakarta.transaction.Transactional;
import org.example.backend.model.dto.mypage.OrderRefundDto;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.Refund;
import org.example.backend.repository.order.OrderDetailRepository;
import org.example.backend.repository.order.OrderRepository;
import org.example.backend.repository.order.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyOrderCancelService
 * author : gumiji
 * date : 5/16/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/16/24         gumiji          최초 생성
 */
@Service
public class MyOrderRefundService {
    @Autowired
    RefundRepository refundRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;


    //    TODO: 저장함수 : 주문 테이블 insert + 주문상세(배열) insert(반복문)
//    => 주문 객체 DTO 정의 : (주문상세 객체배열(List), 주문상태 등)
    @Transactional
    public void insertRefund(OrderRefundDto orderRefundDto) { // 리턴 없으면 void

        for (int i = 0; i < orderRefundDto.getOpId().size(); i++) {
            Refund refund  = new Refund(orderRefundDto.getOrderId(),
                    orderRefundDto.getOpId().get(i)
                    ,orderRefundDto.getRefundPrice().get(i),
                    orderRefundDto.getRefundCode(),
                    orderRefundDto.getRefundReason(),
                    "");
//            DB 저장
            refundRepository.save(refund);
        }
        updateRefundCode(orderRefundDto.getOrderId());
    }

//    주문 코드 수정
    public void updateRefundCode(Long orderId) {
        // 주문을 가져와서 orderCode를 'OD0301'로 변경
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()== false) {
            order.get().setOrderCode("OD0301");
            orderRepository.save(order.get());
        }
    }
}
