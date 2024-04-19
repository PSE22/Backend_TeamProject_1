package org.example.backendproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : ShipAddress
 * author : SAMSUNG
 * date : 2024-04-19
 * description : 배송지 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
 */
@Entity
@Table(name="SHIP_ADDRESS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'N'")
@SQLDelete(sql = "UPDATE SHIP_ADDRESS SET STATUS = 'Y' WHERE SHIP_ADDR_ID = ?")

public class ShipAddress extends BaseTimeEntity2 {
    //    ship_addr_id	number
    //    user_id	varchar2(100 byte)
    //    ship_addr	varchar2(200 byte)
    //    ship_addr_name	varchar2(100 byte)
    //    status	char(1 byte)

    @Id
    private Integer ship_addr_id;
    private String user_id;
    private String ship_addr;
    private String ship_addr_name;
}
