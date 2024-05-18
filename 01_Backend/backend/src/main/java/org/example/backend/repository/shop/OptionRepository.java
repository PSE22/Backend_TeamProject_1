package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IOptionDto;
import org.example.backend.model.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : OptionRepository
 * author : sjuk2
 * date : 2024-04-30
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30         sjuk2          최초 생성
 */
@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
    @Query(value = "SELECT OP_ID AS opId\n" +
            ", PD_ID AS pdId\n" +
            ", OP_NAME AS opName\n" +
            ", OP_PRICE AS opPrice\n" +
            ", OP_STOCK AS opStock\n" +
            "FROM TB_OPTION\n" +
            "WHERE PD_ID = :pdId\n" +
            "AND STATUS = 'Y'"
    , nativeQuery = true)
    List<IOptionDto> findAllByPdidContaining(@Param("pdId") Integer pdId);
}
