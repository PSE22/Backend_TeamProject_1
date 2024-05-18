package org.example.backend.repository.shop;

import jakarta.transaction.Transactional;
import org.example.backend.model.entity.EventImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backend.repository.shop
 * fileName : EventImgRepository
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
@Repository
public interface EventImgRepository extends JpaRepository<EventImg, Long> {
    @Query(value = "SELECT *\n" +
            "FROM TB_EVENT_IMG\n" +
            "WHERE EVENT_ID = :eventId", nativeQuery = true)
    List<EventImg> findByEventImg(@Param("eventId") Long eventId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_EVENT_IMG\n" +
                   "SET STATUS = 'N'\n" +
                   "WHERE EVENT_ID = :eventId AND STATUS = 'Y'", nativeQuery = true)
    void deleteByEventId(@Param("eventId") Long eventId);
}
