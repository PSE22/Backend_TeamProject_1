package org.example.backend.service.shop;

import org.example.backend.model.dto.shop.IEventDto;
import org.example.backend.model.entity.Event;
import org.example.backend.model.entity.EventImg;
import org.example.backend.repository.shop.EventImgRepository;
import org.example.backend.repository.shop.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.service.shop
 * fileName : EventService
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
@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventImgRepository eventImgRepository;

    /**
     * 이벤트 전체 조회
     * @return
     */
    public List<Event> findAllEvent(){
        List<Event> list = eventRepository.findAll();
        return list;
    }

    /**
     * 이벤트 상세 조회
     * @param eventId
     * @return
     */
    public Optional<Event> findByEventId(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent;
    }

    /**
     * 이벤트 이미지 조회 (eventId로 찾기)
     * @param eventId
     * @return
     */
    public List<EventImg> findByEventImg(Long eventId) {
        List<EventImg> list = eventImgRepository.findByEventImg(eventId);
        return list;
    }
}
