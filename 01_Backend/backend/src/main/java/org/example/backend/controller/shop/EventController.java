package org.example.backend.controller.shop;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.model.dto.shop.EventDto;
import org.example.backend.model.dto.shop.IEventDto;
import org.example.backend.model.entity.Event;
import org.example.backend.model.entity.EventImg;
import org.example.backend.model.entity.PdQna;
import org.example.backend.service.shop.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.controller.shop
 * fileName : EventController
 * author : SAMSUNG
 * date : 2024-05-09
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-09         SAMSUNG          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@Controller
public class EventController {
    @Autowired
    EventService eventService;

    // 이벤트 전체 조회
    @GetMapping("/event")
    public ResponseEntity<Object> findAllEvent() {
        try {
            List<Event> list = eventService.findAllEvent();
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 이벤트 상세 조회
    @GetMapping("/event/{eventId}")
    public ResponseEntity<Object> findByEventId(@PathVariable Long eventId) {
        try {
            Optional<Event> optionalEvent = eventService.findByEventId(eventId);
            if (optionalEvent.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(optionalEvent.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 이벤트 이미지 조회
    @GetMapping("/eventImg/{eventId}")
    public ResponseEntity<Object> findByEventImg(@PathVariable Long eventId) {
        try {
            List<EventImg> list = eventService.findByEventImg(eventId);
            if (list.isEmpty() == true) {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // 조회 성공
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 이벤트 등록
    @PostMapping("/event")
    public ResponseEntity<Object> create(@RequestBody EventDto eventDto) {
        try {
            Event event2 = eventService.save(eventDto);
            return new ResponseEntity<>(event2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 이벤트 삭제
    @DeleteMapping("/event/deletion/{eventId}")
    public ResponseEntity<Object> delete(@PathVariable Long eventId) {
        try {
            boolean success = eventService.removeById(eventId);
            if(success == true) {
                // 삭제 성공
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실패 (삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
