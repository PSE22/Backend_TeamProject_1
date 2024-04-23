package org.example.backendproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.example.backendproject.model.common.PdIdUserIdPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : Wishlist
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
@Table(name="TB_WISHLIST")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_WISHLIST SET STATUS = 'N' WHERE PD_ID = ? AND USER_ID = ?")
// 복합키 클래스
@IdClass(PdIdUserIdPk.class)
public class Wishlist extends BaseTimeEntity2 {
    @Id
    private Integer pdId;       // 상품 ID
    @Id
    private String userId;      // 회원 ID
}
