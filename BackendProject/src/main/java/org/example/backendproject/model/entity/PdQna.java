package org.example.backendproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backendproject.model.common.BaseTimeEntity2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.backendproject.model.entity
 * fileName : PdQna
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
@Table(name="TB_PD_QNA")
@SequenceGenerator(
        name = "SEQ_TB_PD_QNA_PD_QNA_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PD_QNA_PD_QNA_ID"
        , initialValue = 1
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
@SQLDelete(sql = "UPDATE TB_PD_QNA SET STATUS = 'N' WHERE PD_QNA_ID = ?")
public class PdQna extends BaseTimeEntity2 {
//    pd_qna_id	number
//    user_id	varchar2(100 byte)
//    pd_id	number
//    pd_qna_title	varchar2(500 byte)
//    pd_qna_content	varchar2(4000 byte)
//    add_date	date
//    del_date	date
//    pd_qna_secret	char(1 byte)
//    pd_qna_code	varchar2(100 byte)
//    status	char(1 byte)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PD_QNA_PD_QNA_ID_GENERATOR"
    )
    private Integer pdQnaId;
    private String userId;
    private Integer pdId;
    private String pdQnaTitle;
    private String pdQnaContent;
    private Character pdQnaSecret;
    private String pdQnaCode;
}
