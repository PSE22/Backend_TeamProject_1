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
 * fileName : Point
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
@Table(name="TB_POINT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_POINT SET STATUS = 'N' WHERE POINT_ID = ?")
public class Point extends BaseTimeEntity2 {
    @Id
    private Integer pointId;            // 포인트 Id
    private String userId;              // 회원 Id
    private Integer pointAdd;           // 적립금액
    private String pointCode;           // 포인트 구분코드
    private String pointExpireStatus;   // 포인트 만료여부
    private String addDate;             // 적립일자
    private String modDate;             // 수정일자
    private String delDate;             // 만료일자
    private String status;              // 상태
}
