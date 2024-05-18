package org.example.backend.service.admin;

import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.OrderCancel;
import org.example.backend.model.entity.OrderDetail;
import org.example.backend.model.entity.Refund;
import org.example.backend.repository.order.OrderCancelRepository;
import org.example.backend.repository.order.OrderDetailRepository;
import org.example.backend.repository.order.OrderRepository;
import org.example.backend.repository.order.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Page<Order> findAllById(Long orderId, Pageable pageable) {
        if (orderId != null) {
            return orderRepository.findAllByOrderId(orderId, pageable);
        } else {
            return orderRepository.findAll(pageable);
        }
    }

    public Optional<Order> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order updateOrderCode(Long orderId, String newOrderCode) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    order.setOrderCode(newOrderCode);
                    return orderRepository.save(order);
                })
                .orElse(null);
    }

    public Optional<OrderCancel> getOrderCancelInfo(Long orderId) {
        return orderCancelRepository.findByOrderId(orderId);
    }

    public OrderCancel updateOrderCancelCode(Long orderId, String newCancelCode) {
        return orderCancelRepository.findByOrderId(orderId)
                .map(cancel -> {
                    cancel.setOcCode(newCancelCode);
                    return orderCancelRepository.save(cancel);
                })
                .orElse(null);
    }

    public Optional<Refund> getRefundInfo(Long orderId) {
        return refundRepository.findByOpId(orderId);
    }

    public Refund updateRefundCode(Long orderId, String newRefundCode) {
        return refundRepository.findByOpId(orderId)
                .map(refund -> {
                    refund.setRefundCode(newRefundCode);
                    return refundRepository.save(refund);
                })
                .orElse(null);
    }

    public Optional<List<OrderDetail>> getOrderDetails(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        return Optional.ofNullable(orderDetails.isEmpty() ? null : orderDetails);
    }
}