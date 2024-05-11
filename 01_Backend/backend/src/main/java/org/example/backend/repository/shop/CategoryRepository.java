package org.example.backend.repository.shop;

import org.example.backend.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.backend.repository.shop
 * fileName : CategoryRepository
 * author : SAMSUNG
 * date : 2024-05-10
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10         SAMSUNG          최초 생성
 */
@Repository
public interface CategoryRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT *\n" +
            "FROM TB_PRODUCT\n" +
            "WHERE CATEGORY_CODE = :categoryCode", nativeQuery = true)
    Page<Product> findByCategoryAll(@Param("categoryCode") String categoryCode, Pageable pageable);
}
