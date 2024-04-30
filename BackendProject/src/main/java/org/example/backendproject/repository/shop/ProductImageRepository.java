package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : ProductImageRepository
 * author : sjuk2
 * date : 2024-04-26
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-26         sjuk2          최초 생성
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    @Query(value = "SELECT PD_IMG_URL\n" +
            "FROM TB_PRODUCT_IMAGE\n" +
            "WHERE PD_ID LIKE '%' || :pdId || '%'\n" +
            "ORDER BY PD_IMG_ID"
    , countQuery = "SELECT count(*)" +
            "FROM TB_PRODUCT_IMAGE\n" +
            "WHERE PD_ID LIKE '%' || :pdId || '%'\n" +
            "ORDER BY PD_IMG_ID"
    , nativeQuery = true)
    Optional<ProductImage> selectByPdIdContaining(@Param("pdId") Integer pdId);
}
