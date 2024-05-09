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
 * fileName : Event
 * author : SAMSUNG
 * date : 2024-05-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08         SAMSUNG          최초 생성
 */
@Entity
@Table(name="TB_EVENT")
@SequenceGenerator(
        name = "SEQ_TB_EVENT_EVENT_ID_GENERATOR"
        , sequenceName = "SEQ_TB_EVENT_EVENT_ID"
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
@SQLDelete(sql = "UPDATE TB_EVENT SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE EVENT_ID = ?")
public class Event extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_EVENT_EVENT_ID_GENERATOR")
    private Long eventId;              // 이벤트 ID (PK)
    private String userId;             // 회원 ID (FK)
    private String eventTitle;        // 제목
    private String eventContent;      // 내용
    private String eventCode;         // 게시판분류코드
    private String eventThumbnail;     // 썸네일 URL
}
