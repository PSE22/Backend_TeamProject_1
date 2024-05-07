package org.example.backend.repository.admin;

import org.example.backend.model.entity.admin.AdminProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
    @Query(value = "SELECT * FROM TB_PRODUCT\n" +
            "WHERE PD_NAME LIKE '%' || :pdName || '%'"
            ,countQuery = "SELECT count(*) FROM TB_PRODUCT\n" +
            "WHERE PD_NAME LIKE '%' || :pdName || '%'"
            ,nativeQuery = true
    )
    Page<AdminProduct> findAllByAdminProductNameContaining(@Param("pdName") String pdName,
                                                         Pageable pageable
    );
}
