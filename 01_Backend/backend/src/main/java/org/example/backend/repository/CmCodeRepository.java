package org.example.backend.repository;

import org.example.backend.model.CmCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * packageName : org.example.backend.repository
 * fileName : CmCodeRepository
 * author : kimtaewan
 * date : 2024-05-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10         kimtaewan          최초 생성
 */
@Repository
public interface CmCodeRepository extends JpaRepository<CmCode, String> {
    @Query(value = "SELECT * FROM TB_CM_CODE WHERE UP_CM_CD LIKE 'OD%'", nativeQuery = true)
    List<CmCode> findByUpCmCd();

//    주문 상세 오더코드 쿼리문
    @Query(value = "SELECT\n" +
            "CM_CD_NAME\n" +
            "FROM TB_CM_CODE, TB_ORDER\n" +
            "WHERE ORDER_CODE = CM_CD\n" +
            "AND ORDER_ID = :orderId",
            nativeQuery = true)
    String findByCmCdName(@Param("orderId") Long orderId);
}
