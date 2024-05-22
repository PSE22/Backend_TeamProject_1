package org.example.backend.repository.mypage;

import org.example.backend.model.dto.shop.IQnaDto;
import org.example.backend.model.entity.PdQna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backend.repository.mypage
 * fileName : MyQnaRepository
 * author : gumiji
 * date : 5/7/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/7/24         gumiji          최초 생성
 */
@Repository
public interface MyQnaRepository extends JpaRepository<PdQna, Long> {
    @Query(value = "SELECT\n" +
            "PQ.PD_QNA_TITLE AS pdQnaTitle,\n" +
            "PQ.PD_QNA_CONTENT AS pdQnaContent,\n" +
            "SUBSTR(PQ.ADD_DATE, 1, 10) AS pqAddDate,\n" +
            "SUBSTR(PQR.ADD_DATE, 1, 10) AS pqrAddDate,\n" +
            "PQR.PD_QNA_REPLY_CONTENT AS pdQnaReplyContent,\n" +
            "PQ.PD_QNA_SECRET AS pdQnaSecret,\n" +
            "PD.PD_NAME AS pdName " +
            "FROM TB_PD_QNA PQ, TB_PD_QNA_REPLY PQR, TB_PRODUCT PD\n" +
            "WHERE PQ.PD_QNA_ID = PQR.PD_QNA_ID(+)\n" +
            "AND PQ.PD_ID = PD.PD_ID\n" +
            "AND PQ.STATUS = 'Y'\n" +
            "AND PQ.USER_ID = :userId " +
            "ORDER BY PQ.ADD_DATE DESC",
    countQuery = "SELECT count(*) FROM TB_PD_QNA PQ, TB_PD_QNA_REPLY PQR, TB_PRODUCT PD\n" +
            "WHERE PQ.PD_QNA_ID = PQR.PD_QNA_ID(+)\n" +
            "AND PQ.PD_ID = PD.PD_ID\n" +
            "AND PQ.STATUS = 'Y'\n" +
            "AND PQ.USER_ID = :userId",
    nativeQuery = true)
    Page<IQnaDto> findByUserId(@Param("userId") String userId, Pageable pageable);

//    문의 카운트
    @Query(value ="SELECT count(*) FROM TB_PD_QNA PQ, TB_PD_QNA_REPLY PQR, TB_PRODUCT PD\n" +
            "WHERE PQ.PD_QNA_ID = PQR.PD_QNA_ID(+)\n" +
            "AND PQ.PD_ID = PD.PD_ID\n" +
            "AND PQ.STATUS = 'Y'\n" +
            "AND PQ.USER_ID = :userId\n",
            nativeQuery = true)
    Integer inquiryCount(@Param("userId") String userId);
}
