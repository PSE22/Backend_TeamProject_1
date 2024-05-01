package org.example.backend.model.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.model.common.BaseTimeEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "TB_COUPON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_COUPON SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE CP_ID = ?")
public class AdminCoupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_COUPON_GENERATOR"
    )
    private Long cpId;         // 쿠폰번호 pk
    private Integer pdId;         // 상품번호 fk
    private String cpName;        // 쿠폰명
    private Integer cpDcPrice;    // 할인금액
    private Integer cpDcRate;     // 할인율
    private Integer cpMinPrice;   // 최소사용금액
    private Integer cpMaxDcPrice; // 최대할인금액
    private String cpExpireDate;  // 만료일
}
