package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IEventDto;
import org.example.backend.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backend.repository.shop
 * fileName : EventRepository
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
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}

