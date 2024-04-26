package org.example.backendproject.repository.shop;

import org.example.backendproject.model.dto.shop.IQnaDto;
import org.example.backendproject.model.entity.PdQna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : QnaRepository
 * author : sjuk2
 * date : 2024-04-24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24         sjuk2          최초 생성
 */
@Repository
public interface QnaRepository extends JpaRepository<PdQna, Integer> {
    @Query(value = "SELECT PQ.USER_ID AS userId, PQ.PD_QNA_TITLE AS pdQnaTitle\n" +
            ", PQ.PD_QNA_CONTENT AS pdQnaContent, PQ.ADD_DATE AS pqAddDate\n" +
            ", PQ.PD_QNA_SECRET AS pdQnaSecert, PQI.PD_QNA_IMG_URL AS pdQnaImgUrl\n" +
            ", QR.PD_QNA_REPLY_CONTENT AS pdQnaReplyContent, QR.ADD_DATE AS qrAddDate\n" +
            "FROM TB_PD_QNA PQ, TB_PD_QNA_IMG PQI, TB_PD_QNA_REPLY QR\n" +
            "WHERE PQ.PD_QNA_ID = PQI.PD_QNA_ID(+)\n" +
            "AND PQI.PD_QNA_ID = QR.PD_QNA_ID(+)"
    , countQuery = "SELECT Count(*)\n" +
            "FROM TB_PD_QNA PQ, TB_PD_QNA_IMG PQI, TB_PD_QNA_REPLY QR\n" +
            "WHERE PQ.PD_QNA_ID = PQI.PD_QNA_ID(+)\n" +
            "AND PQI.PD_QNA_ID = QR.PD_QNA_ID(+)"
    , nativeQuery = true)
    Page<IQnaDto> selectByQnaContaining(Integer pdQnaId, Pageable pageable);
}
