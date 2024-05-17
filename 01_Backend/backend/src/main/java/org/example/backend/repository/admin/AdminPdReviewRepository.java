package org.example.backend.repository.admin;

import org.example.backend.model.entity.admin.AdminPdReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPdReviewRepository extends JpaRepository<AdminPdReview, Long> {
    @Query(value = "SELECT * FROM TB_PD_REVIEW\n" +
            "WHERE REVIEW_TITLE LIKE '%' || :reviewTitle || '%'"
            ,countQuery = "SELECT count(*) FROM TB_PD_REVIEW\n" +
            "WHERE REVIEW_TITLE LIKE '%' || :reviewTitle || '%'"
            ,nativeQuery = true
    )
    Page<AdminPdReview> findAllByAdminPdReviewTitleContaining(@Param("reviewTitle") String reviewTitle,
                                                        Pageable pageable
    );
}
