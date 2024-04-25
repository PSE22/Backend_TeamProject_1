package org.example.backend.service;

import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.OrderCancel;
import org.example.backend.model.entity.OrderDetail;
import org.example.backend.model.entity.Refund;
import org.example.backend.repository.OrderCancelRepository;
import org.example.backend.repository.OrderDetailRepository;
import org.example.backend.repository.OrderRepository;
import org.example.backend.repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.backend.service
 * fileName : OrderManageService
 * author : kimtaewan
 * date : 2024-04-25
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성
 */
@Service
public class OrderManageService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderCancelRepository orderCancelRepository;

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order updateOrderCode(Long orderId, String newOrderCode) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setOrderCode(newOrderCode);
            return orderRepository.save(order);
        }
        return null;
    }

    public OrderCancel getOrderCancelInfo(Long orderId) {
        return orderCancelRepository.findById(orderId).orElse(null);
    }

    public Refund getRefundInfo(Long orderId) {
        return refundRepository.findById(orderId).orElse(null);
    }

    public List<OrderDetail> getOrderDetails(Integer orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}