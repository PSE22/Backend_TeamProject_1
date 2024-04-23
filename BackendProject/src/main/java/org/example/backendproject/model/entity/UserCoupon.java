package org.example.backendproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.example.backendproject.model.common.CpIdUserIdPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : UserCoupon
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
@Table(name="TB_USER_COUPON")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_USER_COUPON SET STATUS = 'N' WHERE CP_ID = ? AND USER_ID = ?")
// 복합키 클래스
@IdClass(CpIdUserIdPk.class)
public class UserCoupon extends BaseTimeEntity2 {
    @Id
    private Integer cpId;
    @Id
    private String userId;
    private String ucpAddDate;
    private String ucpUseDate;
}
