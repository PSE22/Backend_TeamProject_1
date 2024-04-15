package org.example.jpaexam.model.entity.advanced;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import org.example.jpaexam.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

// jpa 어노테이션
@Entity
@Table(name = "TB_GALLERY")
@DynamicInsert
@DynamicUpdate
// 롬북 어노테이션
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// soft delete jpa 어노테이션
@Where(clause = "DELETE_YN = 'N'")
@SQLDelete(sql ="UPDATE TB_GALLERY SET DELETE_YN = 'Y', DELETE_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE UUID = ?")
public class GalleryDb extends BaseTimeEntity2 {

    //    TB_FILE_DB : 컬럼과 일치하게 속성 작성
    @Id
    private String uuid;        // 기본키 : 자바생성

    private String galleryTitle;   // 제목

    private String galleryFileName; // 파일명




    @Lob
    private byte[] galleryData;    // 이미지 저장 속성(필드)

    private String galleryFileUrl; // 이미지 url

}
