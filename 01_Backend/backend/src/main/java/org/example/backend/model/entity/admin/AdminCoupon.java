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
@Table(name = "TB_COUPON")
@SequenceGenerator(
        name = "SEQ_TB_COUPON_CP_ID_GENERATOR"
        , sequenceName = "SEQ_TB_COUPON_CP_ID"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_COUPON SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE CP_ID = ?")
public class AdminCoupon extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_COUPON_CP_ID_GENERATOR"
    )
    private Long cpId;            // 쿠폰번호 pk
    private Long pdId;            // 상품번호 fk
    private String cpName;        // 쿠폰명
    private Integer cpDcPrice;    // 할인금액
    private Double cpDcRate;      // 할인율
    private Integer cpMinPrice;   // 최소사용금액
    private Integer cpMaxDcPrice; // 최대할인금액
    private String cpExpireDate;  // 만료일
}
