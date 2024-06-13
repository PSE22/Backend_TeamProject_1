package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IQnaDto;
import org.example.backend.model.entity.PdQna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = "SELECT PQ.USER_ID AS userId\n" +
            ", PQ.PD_QNA_TITLE AS pdQnaTitle\n" +
            ", PQ.PD_QNA_CONTENT AS pdQnaContent\n" +
            ", PQ.ADD_DATE AS pqAddDate\n" +
            ", PQ.PD_QNA_SECRET AS pdQnaSecret\n" +
            ", PQI.PD_QNA_IMG_URL AS pdQnaImgUrl\n" +
            ", QR.PD_QNA_REPLY_CONTENT AS pdQnaReplyContent\n" +
            ", QR.ADD_DATE AS qrAddDate\n" +
            "FROM TB_PD_QNA PQ, TB_PD_QNA_IMG PQI, TB_PD_QNA_REPLY QR\n" +
            "WHERE PQ.PD_QNA_ID = PQI.PD_QNA_ID(+)\n" +
            "AND PQ.PD_ID = :pdId\n" +
            "AND PQ.PD_QNA_ID = QR.PD_QNA_ID(+)\n" +
            "AND PQ.STATUS = 'Y'\n" +
            "ORDER BY pqAddDate DESC"
    , nativeQuery = true)
    Page<IQnaDto> selectByQnaContaining(@Param("pdId")Long pdId, Pageable pageable);
}
