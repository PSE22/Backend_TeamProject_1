package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IProductImgDto;
import org.example.backend.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : ProductImgRepository
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
public interface ProductImgRepository extends JpaRepository<ProductImage, Long> {
    @Query(value = "SELECT PD_IMG_URL AS pdImgUrl\n" +
            "FROM TB_PRODUCT_IMAGE\n" +
            "WHERE PD_ID = :pdId\n" +
            "AND STATUS = 'Y'\n" +
            "ORDER BY PD_IMG_ID"
    , nativeQuery = true)
    List<IProductImgDto> findAllByPdIdContaining(@Param("pdId") Long pdId);
}
