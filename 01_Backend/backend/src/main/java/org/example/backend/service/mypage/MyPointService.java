package org.example.backend.service.mypage;

import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.repository.mypage.PointDetailRepository;
import org.example.backend.repository.mypage.MyPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    PointDetailRepository pointDetailRepository;

    // 사용자 ID에 따른 적립금 상세 정보 조회
    public List<PointDto> getPointDetailsByUserId(String userId) {
        return pointDetailRepository.findPointDetailsByUserId(userId);
    }
    // 만료된 적립금 상태 업데이트
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")  // 매일 자정에 실행
    public void updateExpiredPointsStatus() {
        int updatedCount = pointDetailRepository.updatePointsToExpired();
        System.out.println("Updated " + updatedCount + " expired points to status 'N'");
    }

    @Transactional
    public int testUpdateExpiredPointsStatus() {
        int updatedCount = pointDetailRepository.updatePointsToExpired();
        System.out.println("Updated " + updatedCount + " expired points to status 'N'");
        return updatedCount;
    }
}
