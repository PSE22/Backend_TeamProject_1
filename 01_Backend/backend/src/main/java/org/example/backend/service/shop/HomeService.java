package org.example.backend.service.shop;

import org.example.backend.model.dto.shop.IEventDto;
import org.example.backend.model.entity.Event;
import org.example.backend.repository.shop.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.backend.service.shop
 * fileName : HomeService
 * author : SAMSUNG
 * date : 2024-05-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08         SAMSUNG          최초 생성
 */
@Service
public class HomeService {
    @Autowired
    EventRepository eventRepository;

    /**
     * 이벤트 전체 조회
     * @return
     */
    public List<Event> findAll(){
        List<Event> list = eventRepository.findAll();
        return list;
    }
}
