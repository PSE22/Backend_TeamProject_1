package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity;
import org.example.backend.model.common.PdIdUserIdPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
// 복합키 클래스
@IdClass(PdIdUserIdPk.class)
public class Wishlist extends BaseTimeEntity {
    @Id
    private Long pdId;       // 상품 ID
    @Id
    private String userId;      // 회원 ID
}
