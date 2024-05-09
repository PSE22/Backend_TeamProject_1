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
@Table(name = "TB_PD_QNA")
@SequenceGenerator(
        name = "SEQ_TB_PD_QNA_ID_GENERATOR"
        , sequenceName = "SEQ_TB_PD_QNA_PD_QNA_ID"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_PD_QNA SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE PD_QNA_ID = ?")
public class AdminPdQna extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PD_QNA_ID_GENERATOR"
    )
    private Long pdQnaId;        // 상품 문의번호 pk
    private String userId;       // 회원ID fk
    private Long pdId;           // 상품번호 fk
    private String pdQnaTitle;   // 제목
    private String pdQnaContent; // 내용
    private char pdQnaSecret;    // 비밀글 여부
    private String pdQnaCode;    // 게시판 분류코드
}
