package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IProductDto;
import org.example.backend.model.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : ProductRepository
 * author : SAMSUNG
 * date : 2024-04-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-22         SAMSUNG          최초 생성
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    신상품 전체 조회
    @Query(value = "SELECT *\n" +
            "FROM (\n" +
            "    SELECT *\n" +
            "    FROM TB_PRODUCT\n" +
            "    WHERE STATUS = 'Y'\n" +
            "    ORDER BY ADD_DATE ASC\n" +
            ")\n" +
            "WHERE ROWNUM <= 20"
    , nativeQuery = true)
    List<Product> findAllOrderByAddDate();

//    신상품 전체 조회(높은 가격순)
    @Query(value = "SELECT *\n" +
            "FROM (\n" +
            "    SELECT *\n" +
            "    FROM TB_PRODUCT\n" +
            "    WHERE STATUS = 'Y'\n" +
            "    ORDER BY ADD_DATE ASC\n" +
            ")\n" +
            "WHERE ROWNUM <= 20\n" +
            "ORDER BY PD_PRICE DESC"
            , nativeQuery = true)
    List<Product> findAllOrderByAddDateAndPriceDesc();

//    신상품 전체 조회(낮은 가격순)
    @Query(value = "SELECT *\n" +
            "FROM (\n" +
            "    SELECT *\n" +
            "    FROM TB_PRODUCT\n" +
            "    WHERE STATUS = 'Y'\n" +
            "    ORDER BY ADD_DATE ASC\n" +
            ")\n" +
            "WHERE ROWNUM <= 20\n" +
            "ORDER BY PD_PRICE ASC"
            , nativeQuery = true)
    List<Product> findAllOrderByAddDateAndPrice();
}
