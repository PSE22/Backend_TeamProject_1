package org.example.backend.service.mypage;

import org.example.backend.model.dto.mypage.IOrderCodeDto;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.repository.mypage.MyCouponRepository;
import org.example.backend.repository.mypage.MyOrderCheckRepository;
import org.example.backend.repository.mypage.MyQnaRepository;
import org.example.backend.repository.mypage.MyReviewRepository;
import org.example.backend.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyPageService
 * author : gumiji
 * date : 5/8/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/8/24         gumiji          최초 생성
 */
@Service
public class MyPageService {
    //    쿠폰 카운트
    @Autowired
    MyCouponRepository myCouponRepository;

    public Integer couponCount(String userId) {
        Integer couponCount = myCouponRepository.couponCount(userId);
        return couponCount;
    }

    //    주문 카운트
    @Autowired
    MyOrderCheckRepository myOrderCheckRepository;

    public Integer orderCount(String userId) {
        Integer orderCount = myOrderCheckRepository.orderCount(userId);
        return orderCount;
    }

    //    리뷰 카운트
    @Autowired
    MyReviewRepository myReviewRepository;

    public Integer reviewCount(String userId) {
        Integer reviewCount = myReviewRepository.reviewCount(userId);
        return reviewCount;
    }

//    문의 카운트
    @Autowired
    MyQnaRepository myQnaRepository;
    public Integer inquiryCount(String userId) {
        Integer inquiryCount = myQnaRepository.inquiryCount(userId);
        return inquiryCount;
    }

//    회원명
    @Autowired
    UserRepository userRepository;
    public String findByUserName(String userId){
        String findByUserId = userRepository.findByUserName(userId);
        return findByUserId;
    }

//    주문정보 카운트
    @Autowired
    OrderRepository orderRepository;
    public List<IOrderCodeDto> orderCodeCount(String userId) {
        List<IOrderCodeDto> orderCodeCount = orderRepository.orderCodeCount(userId);
        return orderCodeCount;
    }
}
