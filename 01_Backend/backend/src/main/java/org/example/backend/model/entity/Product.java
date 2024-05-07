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
 * fileName : Product
 * author : kimtaewan
 * date : 2024-04-19

 * description : 상품
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         kimtaewan          최초 생성
 */
@Entity
@Table(name="TB_PRODUCT")
@SequenceGenerator(
        name = "SEQ_TB_PRODUCT_PD_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PRODUCT_PD_ID"
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
@SQLDelete(sql = "UPDATE TB_PRODUCT SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE PD_ID = ?")
public class Product extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    , generator = "SEQ_TB_PRODUCT_PD_ID_GENERATOR")
    private Long pdId;                  // 상품 ID (PK)
    private String userId;              // 회원 ID (FK)
    private String pdName;              // 상품명
    private Integer pdPrice;            // 가격
    private Integer pdStock;            // 재고
    private String categoryCode;        // 카테고리 분류코드
    private String pdThumbnail;         // 썸네일이미지
}
