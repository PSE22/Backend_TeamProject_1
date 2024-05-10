package org.example.backend.model.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.model.common.BaseTimeEntity2;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "TB_PD_REVIEW")
@SequenceGenerator(
        name = "SEQ_TB_PD_REVIEW_REVIEW_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PD_REVIEW_REVIEW_ID"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PD_REVIEW SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE REVIEW_ID = ?")
public class AdminPdReview extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PD_REVIEW_REVIEW_ID_GENERATOR"
    )
    private Long reviewId;          // 리뷰번호 pk
    private String userId;          // 회원ID fk
    private Long pdId;              // 상품번호 fk
    private String reviewTitle;     // 제목
    private String reviewContent;   // 내용
    private Double reviewRate;      // 별점 (0~5 소수점 1자리)
    private String reviewCode;      // 게시판 분류코드
}
