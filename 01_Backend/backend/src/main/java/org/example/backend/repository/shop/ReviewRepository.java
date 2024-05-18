package org.example.backend.repository.shop;

import org.example.backend.model.dto.shop.IReviewDto;
import org.example.backend.model.entity.PdReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backendproject.repository.shop
 * fileName : ReviewRepository
 * author : sjuk2
 * date : 2024-04-24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24         sjuk2          최초 생성
 */
@Repository
public interface ReviewRepository extends JpaRepository<PdReview, Long> {
    @Query(value = "SELECT PR.USER_ID AS userId\n" +
            ", PR.REVIEW_ID AS reviewId\n" +
            ", PR.REVIEW_TITLE AS reviewTitle\n" +
            ", PR.REVIEW_CONTENT AS reviewContent\n" +
            ", PR.REVIEW_RATE AS reviewRate\n" +
            ", PR.ADD_DATE AS addDate\n" +
            ", PRI.REVIEW_IMG_URL AS reviewImgUrl\n" +
            ", PD.PD_NAME AS pdName\n" +
            "FROM TB_PD_REVIEW PR, TB_PD_REVIEW_IMG PRI, TB_PRODUCT PD\n" +
            "WHERE PR.REVIEW_ID = PRI.REVIEW_ID(+)\n" +
            "AND PD.PD_ID = :pdId\n" +
            "AND PD.PD_ID = PR.PD_ID\n" +
            "AND PD.STATUS = 'Y'\n" +
            "AND PR.STATUS = 'Y'\n" +
            "ORDER BY addDate DESC"
    , nativeQuery = true)
    Page<IReviewDto> selectByReviewContaining(@Param("pdId")Long pdId, Pageable pageable);
}
