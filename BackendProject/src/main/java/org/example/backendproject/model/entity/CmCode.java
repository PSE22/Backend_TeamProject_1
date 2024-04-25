package org.example.backendproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

public class CmCode {
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
    private String addDate;     // 생성 일시
    private String modDate;      // 수정 일시
    private String status;      // 활성화 여부 (데이터 삭제 시 'N' 으로 변경됨)

    @PrePersist
    void OnPrePersist() {
//        insert 하기전에 현재날짜를 넣기 : 날짜포맷(yyyy-MM-dd HH:mm:ss)
        this.addDate = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "Y";
    }

    @PreUpdate
    void OnPreUpdate() {
//        update 하기전에 현재날짜를 넣기
        this.modDate = LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.addDate = this.modDate;
        this.status = "Y";
    }
}
