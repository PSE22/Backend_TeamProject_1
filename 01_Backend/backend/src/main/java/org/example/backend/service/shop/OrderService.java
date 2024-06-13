package org.example.backend.service.shop;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.shop.ICartDto;
import org.example.backend.model.dto.shop.IPointDto;
import org.example.backend.model.dto.shop.IUserCouponDto;
import org.example.backend.model.dto.shop.OrderDto;
import org.example.backend.model.entity.*;
import org.example.backend.repository.UserRepository;
import org.example.backend.repository.order.OrderDetailRepository;
import org.example.backend.repository.order.OrderRepository;
import org.example.backend.repository.shop.*;
import org.example.backend.service.admin.OrderStatsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * packageName : org.example.backendproject.service.shop
 * fileName : OrderService
 * author : SAMSUNG
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         SAMSUNG          최초 생성
 */
@Slf4j
@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShipAddressRepository shipAddressRepository;

    @Autowired
    UserCouponRepository userCouponRepository;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    UsePointRepository usePointRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderStatsService orderStatsService;

    ModelMapper modelMapper = new ModelMapper();    // DTO 변환

    // 주문 정보 저장
    @Transactional
    public Order insert(OrderDto orderDto) {
        // insert 할 때는 DTO -> Entity 형태로 변환해서 insert 해야 함

        // 1) DTO -> Entity 변환
        Order order = modelMapper.map(orderDto, Order.class);

        // 2) 부모 테이블 저장 (부모 먼저)
        Order order2 = orderRepository.save(order);

        // 3) 자식 테이블 저장 (주문 상세 테이블)
        //  트랜잭션(transaction) : CUD 작업에 대해 여러 개가 있을 경우 중간에 에러가 발생하면 모두 롤백함
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (int i = 0; i < orderDto.getOrderDetailList().size(); i++) {
            // 자식 테이블 insert : 기본키(부모쪽 insert 할 때 시퀀스로 생성되어 있음)
            // 생성된 주문 번호 -> 주문 상세 객체에 저장
            OrderDetail tmpOrderDetail = orderDto.getOrderDetailList().get(i);
            tmpOrderDetail.setOrderId(order2.getOrderId());
            orderDetails.add(tmpOrderDetail);
        }
        // DB 저장
        orderDetailRepository.saveAll(orderDetails);

        // 태완님 코드 (주문통계) 호출
         orderStatsService.updateOrderStats();

        return order2;  // 저장된 주문 객체
    }

    // 사용한 쿠폰의 상태 'N' 으로 변경
    public void updateCouponStatus(Long cpId, String userId) {
        userCouponRepository.updateCouponStatus(cpId, userId);
    }

    // 주문자(User) 정보 상세 조회
    public Optional<User> findById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser;
    }

    // 배송지 정보 상세 조회
    public Optional<ShipAddress> findByUserId (String userId) {
        Optional<ShipAddress> optionalShipAddress = shipAddressRepository.findByUserId(userId);
        return optionalShipAddress;
    }

    // 새로운 배송지 저장
    public ShipAddress saveShipAddress(ShipAddress shipAddress) {
        ShipAddress shipAddress2 = shipAddressRepository.save(shipAddress);
        return shipAddress2;
    }


    // 쿠폰 정보 전체 조회
    public List<IUserCouponDto> findAllByUserCoupon(String userId) {
        List<IUserCouponDto> listIUserCouponDto = userCouponRepository.findAllByUserCoupon(userId);
        return listIUserCouponDto;
    }

    // 포인트 잔액 상세 조회
    public Optional<IPointDto> findByResultPoint(String userId) {
        Optional<IPointDto> optionalPoint = pointRepository.findByResultPoint(userId);
        return optionalPoint;
    }

    // 사용 포인트 저장 (insert)
    public UsePoint savePoint(UsePoint usePoint) {
        UsePoint usePoint2 = usePointRepository.save(usePoint);
        return usePoint2;
    }

    // 장바구니 조회
    public List<ICartDto> findByCartUserId(String userId) {
        List<ICartDto> list = cartRepository.findByUserId(userId);
        return list;
    }

    // 주문 후 장바구니 삭제
    public boolean removeByCartId(Long cartId) {
        if(cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return true;
        } else {
            return false;
        }
    }

    public void updateOrderCode(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findByOrderId(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
