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
//    point_id	number
//    user_id	varchar2(100 byte)
//    point_add	number
//    add_date	date
//    point_expire_date	date
//    point_code	varchar2(100 byte)
//    point_expire_status	char(1 byte)
//    status	char(1 byte)
    @Id
    private Integer pointId;
    private String userId;
    private Integer pointAdd;
    private String pointExpireDate;
    private String pointCode;
    private String pointExpireStatus;
}
