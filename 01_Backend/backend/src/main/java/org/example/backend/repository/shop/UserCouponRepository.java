package org.example.backend.repository.shop;

import org.example.backend.model.common.CpIdUserIdPk;
import org.example.backend.model.dto.shop.IUserCouponDto;
import org.example.backend.model.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : UserCouponRepository
 * author : SAMSUNG
 * date : 2024-05-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-01         SAMSUNG          최초 생성
 */
@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupon, CpIdUserIdPk> {
    @Query(value="SELECT UC.USER_ID AS userId,\n" +
            "C.CP_ID AS cpId,\n" +
            "C.PD_ID AS pdId,\n" +
            "C.CP_NAME AS cpName,\n" +
            "C.CP_DC_PRICE AS cpDcPrice,\n" +
            "C.CP_DC_RATE AS cpDcRate, \n" +
            "C.CP_MIN_PRICE AS cpMinPrice,\n" +
            "C.CP_MAX_DC_PRICE AS cpMaxDcPrice \n" +
            "FROM TB_USER_COUPON UC, TB_COUPON C \n" +
            "WHERE UC.CP_ID = C.CP_ID AND UC.STATUS = 'Y' AND UC.USER_ID = :userId ", nativeQuery = true)
    List<IUserCouponDto> findAllByUserCoupon(@Param("userId") String userId);

//    @Query(value = "UPDATE TB_USER_COUPON\n" +
//            "SET STATUS = 'N'\n" +
//            "WHERE CP_ID = :cpId\n" +
//            "AND USER_ID = :userId", nativeQuery = true)
//    UserCoupon updateStatus(@Param("userId")String userId, @Param("cpId")Long cpId);
}
