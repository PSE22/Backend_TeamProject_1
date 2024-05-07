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
@Table(name = "TB_OPTION")
@SequenceGenerator(
        name = "SEQ_TB_OPTION_OP_ID_GENERATOR"
        , sequenceName = "SEQ_TB_OPTION_OP_ID"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "STATUS = 'Y'")
@SQLDelete(sql = "UPDATE TB_OPTION SET STATUS = 'N', DEL_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE OP_ID = ?")
public class AdminOption extends BaseTimeEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SEQ_TB_OPTION_OP_ID_GENERATOR"
    )
    private Long opId;       // 옵션번호 pk
    private Long pdId;       // 상품번호 fk
    private String opName;   // 옵션명
    private Integer opPrice; // 옵션추가가격
    private Integer opStock; // 옵션재고
    private String opCode;   // 옵션분류코드
}
