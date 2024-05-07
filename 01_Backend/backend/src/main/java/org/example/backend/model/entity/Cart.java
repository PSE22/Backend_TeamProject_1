package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : Cart
 * author : SAMSUNG
 * date : 2024-04-19
 * description : 장바구니 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
 */
@Entity
@Table(name = "TB_CART")
@SequenceGenerator(
        name = "SEQ_TB_CART_CART_ID_GENERATOR"
        , sequenceName = "SEQ_TB_CART_CART_ID"
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate

public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    , generator = "SEQ_TB_CART_CART_ID_GENERATOR")
    private Long cartId;
    private String userId;
    private Long opId;
    private Integer cartCount;
}
