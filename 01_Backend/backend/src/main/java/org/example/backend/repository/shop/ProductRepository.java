package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IBestProductDto;
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
    //    모든 상품 조회(검색)
    @Query(value = "SELECT *\n" +
            "FROM TB_PRODUCT\n" +
            "WHERE PD_NAME LIKE '%' || :pdName || '%'\n" +
            "AND STATUS = 'Y'"
            , nativeQuery = true)
    Page<Product> findAllByPdNameContaining(@Param("pdName") String pdName, Pageable pageable);

    //    카테고리별 전체 상품 조회
    @Query(value = "SELECT *\n" +
            "FROM TB_PRODUCT\n" +
            "WHERE CATEGORY_CODE = :categoryCode", nativeQuery = true)
    Page<Product> findByCategoryAll(@Param("categoryCode") String categoryCode, Pageable pageable);

    //     카테고리별 전체 상품 정렬 (신상품순)
    @Query(value = "SELECT *\n" +
            "FROM TB_PRODUCT\n" +
            "WHERE STATUS = 'Y'\n" +
            "AND CATEGORY_CODE = :categoryCode\n" +
            "ORDER BY ADD_DATE ASC", nativeQuery = true)
    Page<Product> findAllByOrderByNewProduct(@Param("categoryCode") String categoryCode, Pageable pageable);

    //     카테고리별 전체 상품 정렬 (인기순)
    @Query(value = "SELECT P.PD_ID AS pdId, P.PD_NAME AS pdName, P.PD_THUMBNAIL pdThumbnail, P.ADD_DATE AS addDate, P.PD_PRICE AS pdPrice\n" +
            "    FROM TB_PRODUCT P, TB_OPTION O, TB_ORDER_DETAIL OD\n" +
            "    WHERE P.PD_ID = O.PD_ID\n" +
            "    AND O.OP_ID = OD.OP_ID\n" +
            "    AND P.STATUS = 'Y'\n" +
            "    AND OD.ORDER_DETAIL_CODE = 'OD0105'\n" +
            "     AND P.CATEGORY_CODE = :categoryCode\n" +
            "GROUP BY P.PD_ID, P.PD_NAME, P.PD_THUMBNAIL, P.ADD_DATE, P.PD_PRICE\n" +
            "ORDER BY SUM(OD.ORDER_DETAIL_CNT) DESC", nativeQuery = true)
    Page<IProductDto> findAllByOrderByOrderDetailCnt(@Param("categoryCode") String categoryCode, Pageable pageable);

//     카테고리별 전체 상품 정렬 (낮은 가격순)
@Query(value = "SELECT *\n" +
        "FROM TB_PRODUCT\n" +
        "WHERE STATUS = 'Y'\n" +
        "AND CATEGORY_CODE = :categoryCode\n" +
        "ORDER BY PD_PRICE ASC", nativeQuery = true)
Page<Product> findAllByOrderByPdPriceAsc(@Param("categoryCode") String categoryCode, Pageable pageable);

//     카테고리별 전체 상품 정렬 (높은 가격순)
@Query(value = "SELECT *\n" +
        "FROM TB_PRODUCT\n" +
        "WHERE STATUS = 'Y'\n" +
        "AND CATEGORY_CODE = :categoryCode\n" +
        "ORDER BY PD_PRICE DESC", nativeQuery = true)
Page<Product> findAllByOrderByPdPriceDesc(@Param("categoryCode") String categoryCode, Pageable pageable);

    //    베스트 상품 3개 조회
    @Query(value = "SELECT pdId, pdName, pdThumbnail, addDate, pdPrice\n" +
            "FROM\n" +
            "(SELECT P.PD_ID AS pdId, P.PD_NAME AS pdName, P.PD_THUMBNAIL pdThumbnail, P.ADD_DATE AS addDate, P.PD_PRICE AS pdPrice\n" +
            "FROM TB_PRODUCT P, TB_OPTION O, TB_ORDER_DETAIL OD\n" +
            "WHERE P.PD_ID = O.PD_ID\n" +
            "AND O.OP_ID = OD.OP_ID\n" +
            "AND P.STATUS = 'Y'\n" +
            "AND OD.ORDER_DETAIL_CODE = 'OD0105'\n" +
            "GROUP BY P.PD_ID, P.PD_NAME, P.PD_THUMBNAIL, P.ADD_DATE, P.PD_PRICE\n" +
            "ORDER BY SUM(OD.ORDER_DETAIL_CNT) DESC, addDate ASC)\n" +
            "WHERE ROWNUM <= 3"
            , nativeQuery = true)
    List<IBestProductDto> findThreeBestProductOrderByAddDate();

    //    베스트 상품 전체 조회(일간 판매량 높은순)
    @Query(value = "SELECT pdId, pdName, pdThumbnail, addDate, pdPrice\n" +
            "FROM\n" +
            "(SELECT P.PD_ID AS pdId, P.PD_NAME AS pdName, P.PD_THUMBNAIL pdThumbnail, P.ADD_DATE AS addDate, P.PD_PRICE AS pdPrice\n" +
            "FROM TB_PRODUCT P, TB_OPTION O, TB_ORDER_DETAIL OD\n" +
            "WHERE P.PD_ID = O.PD_ID\n" +
            "AND O.OP_ID = OD.OP_ID\n" +
            "AND P.STATUS = 'Y'\n" +
            "AND OD.ORDER_DETAIL_CODE = 'OD0105'\n" +
            "AND SUBSTR(OD.ADD_DATE, 1, 10) = TO_CHAR(SYSDATE - 1, 'YYYY-MM-DD')\n" +
            "GROUP BY P.PD_ID, P.PD_NAME, P.PD_THUMBNAIL, P.ADD_DATE, P.PD_PRICE\n" +
            "ORDER BY SUM(OD.ORDER_DETAIL_CNT) DESC, addDate ASC)\n" +
            "WHERE ROWNUM <= 20"
            , nativeQuery = true)
    List<IBestProductDto> findAllBestProductOrderByCountDay();

    //    베스트 상품 전체 조회(월간 판매량 높은순)
    @Query(value = "SELECT pdId, pdName, pdThumbnail, addDate, pdPrice\n" +
            "FROM\n" +
            "(SELECT P.PD_ID AS pdId, P.PD_NAME AS pdName, P.PD_THUMBNAIL pdThumbnail, P.ADD_DATE AS addDate, P.PD_PRICE AS pdPrice\n" +
            "FROM TB_PRODUCT P, TB_OPTION O, TB_ORDER_DETAIL OD\n" +
            "WHERE P.PD_ID = O.PD_ID\n" +
            "AND O.OP_ID = OD.OP_ID\n" +
            "AND P.STATUS = 'Y'\n" +
            "AND OD.ORDER_DETAIL_CODE = 'OD0105'\n" +
            "AND SUBSTR(OD.ADD_DATE, 1, 7) = TO_CHAR(SYSDATE - 1, 'YYYY-MM')\n" +
            "GROUP BY P.PD_ID, P.PD_NAME, P.PD_THUMBNAIL, P.ADD_DATE, P.PD_PRICE\n" +
            "ORDER BY SUM(OD.ORDER_DETAIL_CNT) DESC, addDate ASC)\n" +
            "WHERE ROWNUM <= 20"
            , nativeQuery = true)
    List<IBestProductDto> findAllBestProductOrderByCountMonth();

    //    베스트 상품 전체 조회(연간 판매량 높은순)
    @Query(value = "SELECT pdId, pdName, pdThumbnail, addDate, pdPrice\n" +
            "FROM\n" +
            "(SELECT P.PD_ID AS pdId, P.PD_NAME AS pdName, P.PD_THUMBNAIL pdThumbnail, P.ADD_DATE AS addDate, P.PD_PRICE AS pdPrice\n" +
            "FROM TB_PRODUCT P, TB_OPTION O, TB_ORDER_DETAIL OD\n" +
            "WHERE P.PD_ID = O.PD_ID\n" +
            "AND O.OP_ID = OD.OP_ID\n" +
            "AND P.STATUS = 'Y'\n" +
            "AND OD.ORDER_DETAIL_CODE = 'OD0105'\n" +
            "AND SUBSTR(OD.ADD_DATE, 1, 4) = TO_CHAR(SYSDATE - 1, 'YYYY')\n" +
            "GROUP BY P.PD_ID, P.PD_NAME, P.PD_THUMBNAIL, P.ADD_DATE, P.PD_PRICE\n" +
            "ORDER BY SUM(OD.ORDER_DETAIL_CNT) DESC, addDate ASC)\n" +
            "WHERE ROWNUM <= 20"
            , nativeQuery = true)
    List<IBestProductDto> findAllBestProductOrderByCountYear();

    //    신상품 전체 조회
    @Query(value = "SELECT *\n" +
            "FROM (\n" +
            "SELECT *\n" +
            "FROM TB_PRODUCT\n" +
            "WHERE STATUS = 'Y'\n" +
            "ORDER BY ADD_DATE ASC\n" +
            ")\n" +
            "WHERE ROWNUM <= 20"
            , nativeQuery = true)
    List<Product> findAllNewProductOrderByAddDate();

    //    신상품 전체 조회(높은 가격순)
    @Query(value = "SELECT *\n" +
            "FROM (\n" +
            "SELECT *\n" +
            "FROM TB_PRODUCT\n" +
            "WHERE STATUS = 'Y'\n" +
            "ORDER BY ADD_DATE ASC\n" +
            ")\n" +
            "WHERE ROWNUM <= 20\n" +
            "ORDER BY PD_PRICE DESC"
            , nativeQuery = true)
    List<Product> findAllNewProductOrderByAddDateAndPriceDesc();

    //    신상품 전체 조회(낮은 가격순)
    @Query(value = "SELECT *\n" +
            "FROM (\n" +
            "SELECT *\n" +
            "FROM TB_PRODUCT\n" +
            "WHERE STATUS = 'Y'\n" +
            "ORDER BY ADD_DATE ASC\n" +
            ")\n" +
            "WHERE ROWNUM <= 20\n" +
            "ORDER BY PD_PRICE ASC"
            , nativeQuery = true)
    List<Product> findAllNewProductOrderByAddDateAndPrice();
}
