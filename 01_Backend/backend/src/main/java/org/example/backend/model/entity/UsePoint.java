package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity;
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
public class UsePoint extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_USE_POINT_USE_POINT_ID_GENERATOR")
    private Long usePointId;         // 사용 포인트 ID
    private Long orderId;            // 주문 ID
    private Integer usePointPrice;   // 차감금액
}
