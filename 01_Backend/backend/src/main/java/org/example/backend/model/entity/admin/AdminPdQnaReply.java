package org.example.backend.model.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.model.common.BaseTimeEntity2;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "TB_PD_QNA_REPLY")
@SequenceGenerator(
        name = "SEQ_TB_PD_QNA_REPLY_PD_QNA_REPLY_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PD_QNA_REPLY_PD_QNA_REPLY_ID"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PD_QNA_REPLY SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE PD_QNA_REPLY_ID = ?")
public class AdminPdQnaReply extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PD_QNA_REPLY_PD_QNA_REPLY_ID_GENERATOR"
    )
    private Long pdQnaReplyId;        // 댓글ID pk
    private Long pdQnaId;             // 상품문의번호 fk
    private String pdQnaReplyContent; // 답변 내용
}
