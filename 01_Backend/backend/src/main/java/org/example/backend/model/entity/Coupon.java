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
@SQLDelete(sql = "UPDATE TB_COUPON SET STATUS = 'N' WHERE CP_ID = ?")
public class Coupon extends BaseTimeEntity2 {
//    cp_id	number
//    pd_id	number
//    cp_name	varchar2(500 byte)
//    cp_dc_price	number
//    cp_dc_rate	number
//    cp_min_price	number
//    cp_max_dc_price	number
//    cp_expire_date	date
//    status	char(1 byte)

    @Id
    private Integer cpId;
    private Integer pdId;
    private String cpName;
    private Integer cpDcPrice;
    private Double cpDcRate;
    private Integer cpMinPrice;
    private Integer cpMaxDcPrice;
    private String cpExpireDate;
}
