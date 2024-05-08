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
 * fileName : PdQna
 * author : gumiji
 * date : 5/7/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 5/7/24         gumiji          최초 생성
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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_PD_QNA_PD_QNA_ID_GENERATOR"
    )
    private Integer pdQnaId;    // 문의번호
    private String userId;  // 회원아이디
    private Integer pdId;   // 상품번호
    private String pdQnaTitle;  // 제목
    private String pdQnaContent;    // 내용
    private Character pdQnaSecret;  // 비밀글
    private String pdQnaCode;   // 게시판 분류코드
}
