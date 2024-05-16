package org.example.backend.controller.mypage;

import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.service.mypage.MyPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * packageName : org.example.backend.controller.mypage
 * fileName : PointDetailController
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

@RestController
@RequestMapping("/api/mypage")
public class MyPointDetailController {

    @Autowired
    MyPointService myPointService;


    // 사용자의 적립금 상세 정보 조회
    @GetMapping("/point/{userId}")
    public ResponseEntity<List<PointDto>> getPointsByPeriod(@PathVariable String userId,
                                                            @RequestParam String  startDate,
                                                            @RequestParam String  endDate) {
        List<PointDto> point = myPointService.getPointByPeriod(userId, startDate, endDate);
        if (point.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(point, HttpStatus.OK);
    }


    // 테스트용 만료 포인트 업데이트 메서드
    @GetMapping("/testUpdateExpiredPointsStatus")
    public ResponseEntity<String> updateExpiredPoint() {
        try {
            int updatedCount = myPointService.testUpdateExpiredPointsStatus();
            return new ResponseEntity<>("Updated " + updatedCount + " expired points.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update expired points: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
