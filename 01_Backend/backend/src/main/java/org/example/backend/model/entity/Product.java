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
 * fileName : Product
 * author : SAMSUNG
 * date : 2024-04-19

 * description : 상품 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
 */
@Entity
@Table(name="TB_PRODUCT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PRODUCT SET STATUS = 'N' WHERE PD_ID = ?")
public class Product extends BaseTimeEntity2 {
//    pd_id	number
//    user_id	varchar2(100 byte)
//    pd_name	varchar2(500 byte)
//    pd_price	number
//    pd_stock	number
//    add_date	date
//    update	date
//    del_date	date
//    category_code	varchar2(100 byte)
//    status	char(1 byte)
    @Id
    private Integer pdId;
    private String userId;
    private String pdName;
    private Integer pdPrice;
    private Integer pdStock;
    private String categoryCode;
}
