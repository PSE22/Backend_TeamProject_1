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
 * fileName : ProductImage
 * author : kimtaewan
 * date : 2024-04-25
 * description : 상품 이미지
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-25         kimtaewan          최초 생성

 */
@Entity
@Table(name="TB_PRODUCT_IMAGE")
@SequenceGenerator(
        name = "SEQ_TB_PRODUCT_IMAGE_PD_IMG_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PRODUCT_IMAGE_PD_IMG_ID"
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PRODUCT_IMAGE SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE PD_IMG_ID = ?")
public class ProductImage extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PRODUCT_IMAGE_PD_IMG_ID_GENERATOR")
    private Long pdImgId;       // 상품이미지 ID (PK)
    private Long pdId;          // 상품 ID (FK)
    private String pdImgUrl;    // 이미지 URL
}
