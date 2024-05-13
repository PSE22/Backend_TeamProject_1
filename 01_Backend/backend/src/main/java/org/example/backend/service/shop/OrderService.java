package org.example.backend.service.shop;

import jakarta.transaction.Transactional;
import org.example.backend.model.dto.shop.IPointDto;
import org.example.backend.model.dto.shop.IUserCouponDto;
import org.example.backend.model.dto.shop.OrderDto;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.OrderDetail;
import org.example.backend.model.entity.ShipAddress;
import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.repository.order.OrderDetailRepository;
import org.example.backend.repository.order.OrderRepository;
import org.example.backend.repository.shop.PointRepository;
import org.example.backend.repository.shop.ShipAddressRepository;
import org.example.backend.repository.shop.UserCouponRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    // DTO 변환
    ModelMapper modelMapper = new ModelMapper();
    /**
     * 저장 함수
     * @param orderDto
     * @return
     */
    // 저장함수: 주문 테이블 insert + 주문 상세 (배열) insert(반복문)
    //      => 주문 객체 DTO 정의 : 주문상세 객체배열(List), 주문상태 등
    @Transactional
    public Order insert(OrderDto orderDto) {
        // insert 할 때는 DTO -> Entity 형태로 변환해서 insert 해야 함
        //      방법 (1) : 직접 변환 로직 코딩
        //      방법 (2) : 자동 변환 패키지 사용 (ModelMapper 패키지, 단점: 성능저하)

        // TODO: 1) DTO -> Entity 변환
        // 사용법 : modelMapper.map(DTO_클래스, 엔티티명.class);
        Order order = modelMapper.map(orderDto, Order.class);

        // TODO: 2) 부모 테이블 저장 (부모 먼저)
        Order order2 = orderRepository.save(order);

        // TODO: 3) 자식 테이블 저장 (주문 상세 테이블)
        //  트랜잭션(transaction) :
        //      - CUD 작업에 대해 여러 개가 있을 경우 중간에 에러가 발생하면 모두 롤백함
        //      - @Transactional 어노테이션 사용, 함수 위에 붙임
        for (int i = 0; i < orderDto.getOrderDetailList().size(); i++) {
            // 자식 테이블 insert : 기본키(부모쪽 insert 할 때 시퀀스로 생성되어 있음)
            // 생성된 주문 번호 -> 주문 상세 객체에 저장
//            simpleOrderDto.getSimpleOrderDetailList().get(i).setSono(simpleOrder2.getSono());

            OrderDetail tmpOrderDetail = orderDto.getOrderDetailList().get(i);
            tmpOrderDetail.setOrderId(order2.getOrderId());
            // DB 저장
            orderDetailRepository.save(tmpOrderDetail);
        }
        return order2;  // 저장된 주문 객체
    }

    /**
     * 주문자(User) 정보 상세 조회
     * @param userId
     * @return
     */
    public Optional<User> findById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser;
    }

    /**
     * 배송지 정보 상세 조회
     * @param userId
     * @return
     */
    public Optional<ShipAddress> findByUserId (String userId) {
        Optional<ShipAddress> optionalShipAddress = shipAddressRepository.findByUserId(userId);
        return optionalShipAddress;
    }

    /**
     * 쿠폰 정보 전체 조회
     * @param userId
     * @return
     */
    public List<IUserCouponDto> findAllByUserCoupon(String userId) {
        List<IUserCouponDto> listIUserCouponDto = userCouponRepository.findAllByUserCoupon(userId);
        return listIUserCouponDto;
    }

    /**
     * 포인트 잔액 상세 조회
     * @param userId
     * @return
     */
    public Optional<IPointDto> findByResultPoint(String userId) {
        Optional<IPointDto> optionalPoint = pointRepository.findByResultPoint(userId);
        return optionalPoint;
    }

    public void updateOrderCode(Order order) {
        orderRepository.save(order);
    }

    public Optional<Order> findByOrderId(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
