package org.example.backendproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : PdReview
 * author : SAMSUNG
 * date : 2024-04-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
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
//    review_id	number
//    user_id	varchar2(100 byte)
//    pd_id	number
//    review_title	varchar2(500 byte)
//    review_content	varchar2(4000 byte)
//    review_rate	number
//    add_date	date
//    del_date	date
//    review_code	varchar2(100 byte)
//    status	char(1 byte)
    @Id
    private Integer reviewId;
    private String userId;
    private Integer pdId;
    private String reviewTitle;
    private String reviewContent;
    private Integer reviewRate;
    private String reviewCode;
}
