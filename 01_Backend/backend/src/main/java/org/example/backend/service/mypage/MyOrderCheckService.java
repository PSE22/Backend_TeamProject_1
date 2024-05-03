package org.example.backend.service.mypage;

import org.example.backend.model.dto.mypage.OrderCheckDto;
import org.example.backend.repository.mypage.MyOrderCheckRepository;
import org.example.backend.repository.mypage.MyWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyOrderCheck
 * author : GGG
 * date : 2024-05-02
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02         GGG          최초 생성
 */
@Service
public class MyOrderCheckService {

    @Autowired
    MyOrderCheckRepository myOrderCheckRepository;

//    전체조회
public List<OrderCheckDto> getOrderCheck(String userId)
{
    List<OrderCheckDto> list = myOrderCheckRepository.selectOrderCheck(userId);
    return list;
}
}
