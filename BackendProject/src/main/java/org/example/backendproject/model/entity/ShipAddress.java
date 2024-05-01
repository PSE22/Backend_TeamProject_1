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
@Table(name="TB_SHIP_ADDRESS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_SHIP_ADDRESS SET STATUS = 'N' WHERE SHIP_ADDR_ID = ?")
public class ShipAddress{
    @Id
    private Integer shipAddrId;
    private String userId;
    private String shipAddr;
    private String shipAddrName;
    private String shipAddr2;
    private String postcode;
    private String status;
}
