package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.ICouponDto;
import org.example.backend.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : CouponRepository
 * author : sjuk2
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         sjuk2          최초 생성
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    @Query(value = "SELECT CP_ID AS cpId\n" +
            ", PD_ID AS pdId\n" +
            ", CP_NAME AS cpName\n" +
            ", CP_DC_PRICE AS cpDcPrice\n" +
            ", CP_DC_RATE AS cpDcRate\n" +
            ", CP_MIN_PRICE AS cpMinPrice\n" +
            ", CP_MAX_DC_PRICE AS cpMaxDcPrice\n" +
            ", CP_EXPIRE_DATE AS cpExpireDate\n" +
            "FROM TB_COUPON\n" +
            "WHERE PD_ID = :pdId\n" +
            "AND STATUS = 'Y'"
    , nativeQuery = true)
    List<ICouponDto> findAllByPdidContaining(@Param("pdId") Integer pdId);
}
