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
 * fileName : Coupon
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
@Table(name="TB_COUPON")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_COUPON SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE CP_ID = ?")
public class Coupon extends BaseTimeEntity2 {
    @Id
    private Long cpId;                  // 쿠폰 ID
    private Long pdId;                  // 상품 ID
    private String cpName;              // 쿠폰명
    private Integer cpDcPrice;          // 할인금액
    private Double cpDcRate;            // 할인율
    private Integer cpMinPrice;         // 최소사용금액
    private Integer cpMaxDcPrice;       // 최대할인금액
    private String cpExpireDate;        // 쿠폰만료일
}
