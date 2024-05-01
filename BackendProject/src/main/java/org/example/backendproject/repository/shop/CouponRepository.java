package org.example.backendproject.repository.shop;

import org.example.backendproject.model.common.CpIdUserIdPk;
import org.example.backendproject.model.dto.mypage.ICouponDto;
import org.example.backendproject.model.dto.shop.ICartDto;
import org.example.backendproject.model.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : CouponRepository
 * author : gumiji
 * date : 4/25/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/25/24         gumiji          최초 생성
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, CpIdUserIdPk> {
    @Query(value = "SELECT \n" +
            "UCP.CP_ID AS cpId,\n" +
            "UCP.USER_ID AS userId,\n" +
            "UCP.ADD_DATE AS addDate,\n" +
            "CP.CP_NAME AS cpName,\n" +
            "CP.CP_DC_PRICE AS cpDcPrice,\n" +
            "CP.CP_DC_RATE AS cpDcRate,\n" +
            "CP.CP_MIN_PRICE AS cpMinPrice,\n" +
            "CP.CP_MAX_DC_PRICE AS cpMaxDcPrice,\n" +
            "CP.CP_EXPIRE_DATE AS cpExpireDate\n" +
            "FROM TB_USER_COUPON UCP, TB_COUPON CP\n" +
            "WHERE UCP.CP_ID = CP.CP_ID " +
            "AND CP.STATUS = 'Y'"
            ,countQuery = "SELECT \n" +
                    "count(*)\n" +
                    "FROM TB_USER_COUPON UCP, TB_COUPON CP\n" +
                    "WHERE UCP.CP_ID = CP.CP_ID" +
            "AND CP.STATUS = 'Y'"
            ,nativeQuery = true
    )
    Page<ICouponDto> selectByCouponContaining(String cpName, Pageable pageable);
}
