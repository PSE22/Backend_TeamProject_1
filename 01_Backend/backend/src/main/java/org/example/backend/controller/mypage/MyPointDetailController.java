package org.example.backend.controller.mypage;

import org.example.backend.model.dto.mypage.PointDto;
import org.example.backend.service.mypage.MyPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<PointDto>> getPointDetailsByUserId(@PathVariable String userId) {
        List<PointDto> pointDetails = myPointService.getPointDetailsByUserId(userId);
        if (pointDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pointDetails, HttpStatus.OK);
    }
}