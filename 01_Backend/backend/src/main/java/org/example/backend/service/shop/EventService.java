package org.example.backend.service.shop;

import jakarta.transaction.Transactional;
import org.example.backend.model.dto.shop.EventDto;
import org.example.backend.model.dto.shop.IEventDto;
import org.example.backend.model.entity.Event;
import org.example.backend.model.entity.EventImg;
import org.example.backend.model.entity.Order;
import org.example.backend.model.entity.PdQna;
import org.example.backend.repository.shop.EventImgRepository;
import org.example.backend.repository.shop.EventRepository;
import org.modelmapper.ModelMapper;
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

    ModelMapper modelMapper = new ModelMapper();    // DTO 변환

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

    /**
     * 이벤트 등록
     * @param eventDto
     * @return
     */
    @Transactional
    public Event save(EventDto eventDto) {
        // DTO -> Entity 변환
        Event event = modelMapper.map(eventDto, Event.class);
        // 이벤트 테이블 저장 (부모 테이블)
        Event event2 = eventRepository.save(event);
        // 이벤트 이미지 테이블 저장 (자식 테이블)
        EventImg eventImg = new EventImg(null, event2.getEventId(), eventDto.getEventImgUrl());
        eventImgRepository.save(eventImg);
        return event2;
    }

    /**
     * 이벤트 삭제
     * @param eventId
     * @return
     */
    public boolean removeById(Long eventId) {
        if(eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
            eventImgRepository.deleteByEventId(eventId);
            return true;
        } else {
            return false;
        }
    }
}
