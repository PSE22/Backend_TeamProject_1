package org.example.backend.service.mypage;

import org.example.backend.repository.mypage.PointDetailRepository;
import org.example.backend.repository.mypage.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backend.service.mypage
 * fileName : MyPointService
 * author : GGG
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         GGG          최초 생성
 */
@Service
public class MyPointService {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    PointDetailRepository pointDetailRepository;

//   포인트 전체조회 함수

//    PointDetail 의 잔액 조회하는 함수

}
