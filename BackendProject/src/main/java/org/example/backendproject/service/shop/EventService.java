package org.example.backendproject.service.shop;

import org.example.backendproject.model.entity.EventImg;
import org.example.backendproject.repository.shop.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : EventService
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
@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    /**
     * 이벤트 이미지 전체조회
     * @return
     */
    public List<EventImg> findAll() {
        List<EventImg> list = eventRepository.findAll();
        return list;
    }
}
