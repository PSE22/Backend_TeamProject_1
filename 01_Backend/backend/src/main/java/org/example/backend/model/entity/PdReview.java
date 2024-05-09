package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backend.model.entity
 * fileName : PdReview
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
@Entity
@Table(name="TB_PD_REVIEW")
@SequenceGenerator(
        name = "SEQ_TB_PD_REVIEW_REVIEW_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PD_REVIEW_REVIEW_ID"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PD_REVIEW SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE REVIEW_ID = ?")
public class PdReview extends BaseTimeEntity2 {
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PD_REVIEW_REVIEW_ID_GENERATOR")
    @Id
    private Long reviewId;              // 리뷰 ID
    private String userId;              // 회원 ID (FK)
    private Long pdId;                  // 상품 ID (FK)
    private String reviewTitle;         // 제목
    private String reviewContent;       // 내용
    private Integer reviewRate;         // 별점
    private String reviewCode;          // 게시판분류코드

    public PdReview(String userId, Long pdId, String reviewTitle, String reviewContent, Integer reviewRate, String reviewCode) {
        this.userId = userId;
        this.pdId = pdId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewRate = reviewRate;
        this.reviewCode = reviewCode;
    }
}
