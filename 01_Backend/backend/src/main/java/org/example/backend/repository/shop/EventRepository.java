package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IEventDto;
import org.example.backend.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Query(value = "SELECT \n" +
            "E.EVENT_TITLE AS eventTitle,\n" +
            "E.EVENT_CONTENT AS eventContent,\n" +
            "E.ADD_DATE AS eventAddDate,\n" +
            "E.EVENT_THUMBNAIL AS eventThumbnail,\n" +
            "EI.EVENT_IMG_URL AS eventImgUrl\n" +
            "FROM TB_EVENT E, TB_EVENT_IMG EI\n" +
            "WHERE E.EVENT_ID = EI.EVENT_ID(+) AND E.STATUS = 'Y'", nativeQuery = true)
    List<IEventDto> findByEventImg(Long eventId);
}

