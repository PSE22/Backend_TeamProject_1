package org.example.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.apache.kafka.common.protocol.types.Field;
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
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
 */
@Entity
@Table(name="TB_USE_POINT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_USE_POINT SET STATUS = 'N' WHERE USE_POINT_ID = ?")
public class UsePoint extends BaseTimeEntity2 {

    @Id
    private Integer usePointId;      // 사용한 포인트 ID
    private String  userId;          // userId
    private Integer usePointPrice;   // 차감금액
    private String  addDate;         // 등록일자
    private String  modDate;         // 수정일자
    private String  delDate;         // 삭제일자
    private String  status;          // 상태
}
