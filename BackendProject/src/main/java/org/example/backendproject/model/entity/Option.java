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
 * fileName : Option
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
@Table(name="TB_OPTION")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_OPTION SET STATUS = 'N' WHERE OP_ID = ?")
public class Option extends BaseTimeEntity2 {
//    op_id	number
//    pd_id	number
//    op_name	varchar2(500 byte)
//    op_price	number
//    op_stock	number
//    add_date	date
//    update	date
//    del_date	date
//    op_code	varchar2(100 byte)
//    status	char(1 byte)

    @Id
    private Integer opId;
    private Integer pdId;
    private String opName;
    private Integer opPrice;
    private Integer opStock;
    private String opCode;
}
