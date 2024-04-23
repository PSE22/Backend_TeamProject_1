package org.example.backendproject.repository.shop;

import org.example.backendproject.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM TB_PRODUCT\n" +
                   "WHERE PD_ID LIKE '%' || :pdName || '%'",
            countQuery = "SELECT count(*) FROM TB_PRODUCT\n" +
                         "WHERE PD_ID LIKE '%' || :pdName || '%'", nativeQuery = true)
    Page<Product> findAllByPdIdContaining (@Param("pdName") String pdName, Pageable pageable);
}
