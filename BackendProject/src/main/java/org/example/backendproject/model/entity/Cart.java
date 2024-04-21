package org.example.backendproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity;
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
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Cart extends BaseTimeEntity {
    //    cart_id	number
    //    user_id	varchar2(100 byte)
    //    op_id	number
    //    cart_count	number
    //    add_date	date

    @Id
    private Integer cartId;
    private String userId;
    private Integer opId;
    private Integer cartCount;
}