package org.example.backendproject.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.entity.EventImg;
import org.example.backendproject.service.shop.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName : org.example.backendproject.controller.shop
 * fileName : EventController
 * author : SAMSUNG
 * date : 2024-04-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         SAMSUNG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class EventController {
    @Autowired
    EventService eventService;

    // 이벤트 전체 조회
    @GetMapping("/event")
    public ResponseEntity<Object> findAll() {
        try {
            List<EventImg> eventImgListlist = eventService.findAll();
            if(eventImgListlist.isEmpty() == false) {
                // 조회 성공
                return new ResponseEntity<>(eventImgListlist, HttpStatus.OK);
            } else {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
