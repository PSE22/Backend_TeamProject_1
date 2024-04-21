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
 * fileName : PdReviewImg
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
@Table(name="TB_PD_REVIEW_IMG")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PD_REVIEW_IMG SET STATUS = 'N' WHERE REVIEW_IMG_ID = ?")
public class PdReviewImg extends BaseTimeEntity2 {
//    review_img_id	number
//    review_id	number
//    review_img_url	varchar2(1000 byte)
//    add_date	date
//    del_date	date
//    status	char(1 byte)
    @Id
    private Integer reviewImgId;
    private Integer reviewId;
    private String reviewImgUrl;
}
