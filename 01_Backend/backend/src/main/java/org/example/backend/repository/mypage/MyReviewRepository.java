package org.example.backend.repository.mypage;

import org.example.backend.model.dto.shop.IReviewDto;
import org.example.backend.model.entity.PdReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.backend.repository.mypage
 * fileName : MyReviewRepository
 * author : gumiji
 * date : 5/3/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/3/24         gumiji          최초 생성
 */
@Repository
public interface MyReviewRepository extends JpaRepository<PdReview, Integer> {
    @Query(value = "SELECT\n" +
            "PD.PD_THUMBNAIL AS pdThumbnail,\n" +
            "PD.PD_NAME AS pdName,\n" +
            "PR.REVIEW_RATE AS reviewRate,\n" +
            "PR.REVIEW_TITLE AS reviewTitle,\n" +
            "PR.REVIEW_CONTENT AS reviewContent,\n" +
            "PRI.REVIEW_IMG_URL AS reviewImgUrl,\n" +
            "SUBSTR(PR.ADD_DATE, 1, 10) AS addDate,\n" +
            "PD.PD_ID AS pdId\n" +
            "FROM TB_PD_REVIEW PR, TB_PD_REVIEW_IMG PRI, TB_PRODUCT PD\n" +
            "WHERE PR.REVIEW_ID = PRI.REVIEW_ID(+)\n" +
            "AND PD.PD_ID = PR.PD_ID\n" +
            "AND PD.STATUS = 'Y' " +
            "AND PR.USER_ID = :userId " +
            "ORDER BY PR.ADD_DATE DESC ",
            countQuery = "SELECT count(*) FROM TB_PD_REVIEW PR, TB_PD_REVIEW_IMG PRI, TB_PRODUCT PD\n" +
                    "WHERE PR.REVIEW_ID = PRI.REVIEW_ID(+)\n" +
                    "AND PD.PD_ID = PR.PD_ID\n" +
                    "AND PD.STATUS = 'Y' " +
                    "AND PR.USER_ID = :userId "
            , nativeQuery = true)
    Page<IReviewDto> findByUserId(@Param("userId") String userId, Pageable pageable);

    //    리뷰 카운트
    @Query(value = "SELECT count(*) FROM TB_PD_REVIEW PR, TB_PD_REVIEW_IMG PRI, TB_PRODUCT PD\n" +
            "WHERE PR.REVIEW_ID = PRI.REVIEW_ID(+)\n" +
            "AND PD.PD_ID = PR.PD_ID\n" +
            "AND PD.STATUS = 'Y' AND PR.USER_ID = :userId\n"
            , nativeQuery = true)
    Integer reviewCount(@Param("userId") String userId);
}
