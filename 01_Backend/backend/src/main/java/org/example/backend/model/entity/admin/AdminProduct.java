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
@Table(name = "TB_PRODUCT")
@SequenceGenerator(
        name = "SEQ_TB_PRODUCT_PD_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PRODUCT_PD_ID"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PRODUCT SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE PD_ID = ?")
public class AdminProduct extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PRODUCT_PD_ID_GENERATOR"
    )
    private Long pdId;           // 상품번호 pk
    private String userId;       // 회원ID fk
    private String pdName;       // 상품명
    private Integer pdPrice;     // 가격
    private Integer pdStock;     // 재고
    private String categoryCode; // 카테고리분류코드
    private String pdThumbnail;  // 썸네일URL
}
