package org.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backend.model.entity
 * fileName : EventImg
 * author : SAMSUNG
 * date : 2024-05-09
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-09         SAMSUNG          최초 생성
 */
@Entity
@Table(name = "TB_EVENT_IMG")
@SequenceGenerator(
        name = "SEQ_TB_EVENT_IMG_EVENT_IMG_ID_GENERATOR"
        , sequenceName = "SEQ_TB_EVENT_IMG_EVENT_IMG_ID"
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
@SQLDelete(sql = "UPDATE TB_EVENT_IMG SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE EVENT_IMG_ID = ?")
public class EventImg extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_EVENT_IMG_EVENT_IMG_ID_GENERATOR")
    private Long eventImgId;        // 이미지 ID
    private Long eventId;           // 이벤트 ID
    private String eventImgUrl;     // 이미지 URL
}
