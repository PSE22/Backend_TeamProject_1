package org.example.backend.repository.admin;

import org.example.backend.model.entity.admin.AdminCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCouponRepository extends JpaRepository<AdminCoupon, Long> {
    @Query(value = "SELECT * FROM TB_COUPON\n" +
            "WHERE CP_NAME LIKE '%' || :cpName || '%'"
            ,countQuery = "SELECT count(*) FROM TB_COUPON\n" +
            "WHERE CP_NAME LIKE '%' || :cpName || '%'"
            ,nativeQuery = true
    )
    Page<AdminCoupon> findAllByAdminCouponNameContaining(@Param("cpName") String cpName,
                                 Pageable pageable
    );
}
