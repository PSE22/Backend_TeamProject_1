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
 * fileName : Event
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
@Table(name="TB_EVENT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// soft delete
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_EVENT SET STATUS = 'N' WHERE EVENT_ID = ?")

public class Event extends BaseTimeEntity2 {
//    event_id	number
//    user_id	varchar2(100 byte)
//    event_title	varchar2(500 byte)
//    event_content	varchar2(4000 byte)
//    add_date	date
//    update	date
//    del_date	date
//    event_code	varchar2(100 byte)
//    status	char(1 byte)

    @Id
    private Integer eventId;
    private Integer userId;
    private Integer eventTitle;
    private Integer eventContent;
    private Integer eventCode;
}
