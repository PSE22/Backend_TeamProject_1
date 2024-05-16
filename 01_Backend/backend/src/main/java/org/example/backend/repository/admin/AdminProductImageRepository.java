package org.example.backend.repository.admin;

import org.example.backend.model.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Query(value = "SELECT * FROM TB_PRODUCT_IMAGE\n" +
            "WHERE PD_IMG_ID LIKE '%' || :pdImgId || '%'"
            ,countQuery = "SELECT count(*) FROM TB_PRODUCT_IMAGE\n" +
            "WHERE PD_IMG_ID LIKE '%' || :pdImgId || '%'"
            ,nativeQuery = true
    )
    Page<ProductImage> findAllByAdminProductImagePdImgIdContaining(@Param("pdImgId") Long pdImgId,
                                                           Pageable pageable
    );
}
