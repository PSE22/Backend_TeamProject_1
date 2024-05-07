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
 * fileName : UsePoint
 * author : SAMSUNG
 * date : 2024-04-19
 * description : 사용 포인트 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
 */
@Entity
@Table(name="TB_USE_POINT")
@SequenceGenerator(
        name = "SEQ_TB_USE_POINT_USE_POINT_ID_GENERATOR"
        , sequenceName = "SEQ_TB_USE_POINT_USE_POINT_ID"
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
@SQLDelete(sql = "UPDATE TB_USE_POINT SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE USE_POINT_ID = ?")
public class UsePoint extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_USE_POINT_USE_POINT_ID_GENERATOR")
    private Long usePointId;         // 사용한 포인트 ID
    private String  userId;          // userId
    private Integer usePointPrice;   // 차감금액
}
