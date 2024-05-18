package org.example.backend.service;

import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.OrderCancel;
import org.example.backend.repository.order.OrderCancelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.backend.service
 * fileName : OrderCancelService
 * author : kimtaewan
 * date : 2024-05-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10         kimtaewan          최초 생성
 */
@Service
public class OrderCancelService {
    @Autowired
    OrderCancelRepository orderCancelRepository;

    public void updateOrderCancel(OrderCancel orderCancel) {
        orderCancelRepository.save(orderCancel);
    }

    public Optional<OrderCancel> findByOrderId(Long orderId) {
        return orderCancelRepository.findByOrderId(orderId);
    }
}
