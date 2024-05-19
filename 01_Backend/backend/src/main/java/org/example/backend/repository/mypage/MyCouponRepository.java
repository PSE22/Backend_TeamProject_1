package org.example.backend.repository.mypage;

import org.example.backend.model.common.CpIdUserIdPk;
import org.example.backend.model.dto.shop.ICouponDto;
import org.example.backend.model.entity.UserCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * packageName : org.example.backend.repository.mypage
 * fileName : MyCouponRepository
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
public interface MyCouponRepository extends JpaRepository<UserCoupon, CpIdUserIdPk> {
    @Query(value = "SELECT \n" +
            "UCP.CP_ID AS cpId,\n" +
            "SUBSTR(UCP.ADD_DATE, 1, 10) AS addDate,\n" +
            "CP.CP_NAME AS cpName,\n" +
            "CP.CP_DC_PRICE AS cpDcPrice,\n" +
            "CP.CP_DC_RATE AS cpDcRate,\n" +
            "CP.CP_MIN_PRICE AS cpMinPrice,\n" +
            "CP.CP_MAX_DC_PRICE AS cpMaxDcPrice,\n" +
            "SUBSTR(CP.CP_EXPIRE_DATE, 1, 10) AS cpExpireDate\n" +
            "FROM TB_USER_COUPON UCP, TB_COUPON CP\n" +
            "WHERE UCP.CP_ID = CP.CP_ID " +
            "AND CP.STATUS = 'Y' " +
            "AND UCP.USER_ID = :userId " +
            "ORDER BY CP.CP_EXPIRE_DATE"
            ,countQuery = "SELECT \n" +
            "count(*)\n" +
            "FROM TB_USER_COUPON UCP, TB_COUPON CP\n" +
            "WHERE UCP.CP_ID = CP.CP_ID " +
            "AND CP.STATUS = 'Y' " +
            "AND UCP.USER_ID = :userId "
            ,nativeQuery = true
    )
    Page<ICouponDto> findByUserId(@Param("userId") String userId, Pageable pageable);

//    쿠폰 카운트
    @Query(value = "SELECT count(*)\n" +
            "FROM TB_USER_COUPON UCP, TB_COUPON CP\n" +
            "WHERE UCP.CP_ID = CP.CP_ID\n" +
            "AND CP.STATUS = 'Y' " +
            "AND UCP.USER_ID=:userId ", nativeQuery = true)
    Integer couponCount (@Param("userId") String userId);
}
