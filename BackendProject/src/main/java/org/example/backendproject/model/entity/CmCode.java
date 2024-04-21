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
 * fileName : CmCode
 * author : SAMSUNG
 * date : 2024-04-19
 * description : 공통코드 엔티티
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19         SAMSUNG          최초 생성
 */
@Entity
@Table(name="TB_CM_CODE")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_CM_CODE SET STATUS = 'N' WHERE CM_CD = ?")

public class CmCode extends BaseTimeEntity2 {
//    cm_cd	varchar2(100 byte)
//    up_cm_cd	varchar2(100 byte)
//    cm_cd_name	varchar2(100 byte)
//    cm_cd_comment	varchar2(500 byte)
//    add_date	date
//    mod_date	date
//    status	char(1 byte)

    @Id
    private String cmCd;
    private String upCmCd;
    private String cmCdName;
    private String cmCdComment;
}
