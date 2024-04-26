package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IProductDto;
import org.example.backend.model.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query(value = "SELECT P.PD_ID AS pdId," +
                    "P.PD_NAME AS pdName, " +
                    "P.PD_PRICE AS pdPrice, " +
                    "P.PD_STOCK AS pdStock, " +
                    "P.ADD_DATE AS addDate, " +
                    "P.CATEGORY_CODE AS categoryCode, " +
                    "PD_IMG_URL AS pdImgUrl, " +
                    "OP_ID AS opId, " +
                    "OP_NAME AS opName, " +
                    "OP_PRICE AS opPrice\n" +
                    "FROM TB_PRODUCT P, TB_PRODUCT_IMAGE PI, TB_OPTION OP\n" +
                    "WHERE P.PD_ID = PI.PD_ID AND P.PD_ID = OP.PD_ID(+)",
            countQuery = "SELECT count(*)"+
                           "FROM TB_PRODUCT P, TB_PRODUCT_IMAGE PI, TB_OPTION OP\n" +
                           "WHERE P.PD_ID = PI.PD_ID AND P.PD_ID = OP.PD_ID(+)",
            nativeQuery = true)
    Page<IProductDto> findAllByCategoryCodeContaining (String categoryCode, Pageable pageable);
}
