package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity2;
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
@SequenceGenerator(
        name = "SEQ_TB_SHIP_ADDRESS_SHIP_ADDR_ID_GENERATOR"
        , sequenceName = "SEQ_TB_SHIP_ADDRESS_SHIP_ADDR_ID"
        , allocationSize = 1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_SHIP_ADDRESS SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE SHIP_ADDR_ID = ?")
public class ShipAddress extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_SHIP_ADDRESS_SHIP_ADDR_ID_GENERATOR")
    private Long shipAddrId;        // 배송지 ID (PK)
    private String userId;          // 회원 ID
    private String shipAddr;        // 배송지 주소
    private String shipAddrName;    // 배송지명
    private String shipAddr2;       // 배송지 상세주소
    private String postcode;        // 우편번호
}
