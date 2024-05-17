package org.example.backend.service.mypage;

import jakarta.transaction.Transactional;
import org.example.backend.model.dto.mypage.OrderCancelDto;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.OrderCancel;
import org.example.backend.model.entity.OrderDetail;
import org.example.backend.repository.order.OrderCancelRepository;
import org.example.backend.repository.order.OrderDetailRepository;
import org.example.backend.repository.order.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
public class MyOrderCancelService {
    @Autowired
    OrderCancelRepository orderCancelRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;


    //    TODO: 저장함수 : 주문 테이블 insert + 주문상세(배열) insert(반복문)
//    => 주문 객체 DTO 정의 : (주문상세 객체배열(List), 주문상태 등)
    @Transactional
    public void insert(OrderCancelDto orderCancelDto) { // 리턴 없으면 void

        for (int i = 0; i < orderCancelDto.getOpId().size(); i++) {
            OrderCancel orderCancel  = new OrderCancel(orderCancelDto.getOrderId(),
                    orderCancelDto.getOpId().get(i)
                    ,orderCancelDto.getOcPrice(),
                    orderCancelDto.getOcCode(),
                    orderCancelDto.getOcReason(),
                    "");
//            DB 저장
            orderCancelRepository.save(orderCancel);
        }
    }

//        Order order = new Order();
//        order.setOrderCode("OD0201");
//        orderCancelRepository.save(orderCancel);
////            DB 저장
//        return orderCancel2;    // 저장된 주문 객체
//    }

    //    TODO: 주문 상세조회
    public List<OrderDetail> findByOrderIdAndOpId(Long orderId) {
//        DB 상세조회 실행
        List<OrderDetail> optionalOrderDetail
                = orderDetailRepository.findByOrderIdAndOpId(orderId);
        return optionalOrderDetail;
    }

}
