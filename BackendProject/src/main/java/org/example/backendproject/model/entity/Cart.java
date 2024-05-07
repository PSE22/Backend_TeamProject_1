package org.example.backendproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        , initialValue = 1
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


public class Cart {
    //    cart_id	number
    //    user_id	varchar2(100 byte)
    //    op_id	number
    //    cart_count	number
    //    add_date	date

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_CART_CART_ID_GENERATOR"
    )
    private Integer cartId;
    private String userId;
    private Integer opId;
    private Integer cartCount;
    private String addDate;

    @PrePersist
    void OnPrePersist() {
        this.addDate = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(int cartCount) {
        this.cartCount += cartCount;
    }
}
