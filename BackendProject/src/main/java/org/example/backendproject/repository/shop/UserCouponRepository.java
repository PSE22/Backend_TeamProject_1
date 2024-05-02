package org.example.backendproject.repository.shop;

import org.example.backendproject.model.common.CpIdUserIdPk;
import org.example.backendproject.model.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<UserCoupon> findByUserId(String userId);
}
