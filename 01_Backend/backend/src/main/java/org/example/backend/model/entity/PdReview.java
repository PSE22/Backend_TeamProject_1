package org.example.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PD_REVIEW SET STATUS = 'N' WHERE REVIEW_ID = ?")
public class PdReview extends BaseTimeEntity2 {
    @Id
    private Integer reviewId;
    private String userId;
    private Integer pdId;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewRate;
    private String reviewCode;
}
