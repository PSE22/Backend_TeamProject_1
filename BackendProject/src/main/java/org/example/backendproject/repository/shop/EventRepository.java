package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.EventImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : EventRepository
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
@Repository
public interface EventRepository extends JpaRepository<EventImg, Integer> {
}
