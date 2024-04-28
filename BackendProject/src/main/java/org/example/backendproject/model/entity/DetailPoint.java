package org.example.backendproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.example.backendproject.model.common.PdIdUserIdPk;
import org.example.backendproject.model.common.PtIdUseptIdPk;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : DetailPoint
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
@Table(name="TB_DETAIL_POINT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_DETAIL_POINT SET STATUS = 'N' WHERE USE_POINT_ID = ?")
// 복합키 클래스
@IdClass(PtIdUseptIdPk.class)
public class DetailPoint {
//    use_point_id	number
//    point_id	number
//    point_balance	number
//    status	char(1 byte)
    @Id
    private Integer usePointId;  // 사용 포인트 ID
    @Id
    private Integer pointId;     // 포인트 ID

    private String status = "Y"; // 'Y'로 초기화

    private Integer pointBalance; // 포인트 잔액


}
