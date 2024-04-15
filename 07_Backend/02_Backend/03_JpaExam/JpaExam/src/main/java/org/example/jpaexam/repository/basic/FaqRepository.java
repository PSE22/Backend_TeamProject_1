package org.example.jpaexam.repository.basic;

import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.model.entity.basic.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : FaqRepository
 * author : GGG
 * date : 2024-03-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21         GGG          최초 생성
 */
@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {
    @Query(value =  "SELECT * FROM TB_FAQ T\n" +
            "WHERE T.TITLE LIKE '%'|| :title ||'%'"
            ,countQuery = "SELECT count(*) FROM TB_Faq T\n" +
            "WHERE T.TITLE LIKE '%'|| :title || '%'"
            , nativeQuery = true)

    Page<Faq> findAllByTitleContaining(
            @Param("title") String title,
            Pageable pageable
    );
}
