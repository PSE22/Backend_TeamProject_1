package org.example.jpaexam.model.entity.basic;

import jakarta.persistence.*;
import lombok.*;
import org.example.jpaexam.model.common.BaseTimeEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpaexam.model.entity.basic
 * fileName : Dept
 * author : GGG
 * date : 2024-03-18
 * description : DB 엔티티(JPA 용어)
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-18         GGG          최초 생성
 */
@Entity
@Table(name = "TB_FAQ")   // DB의 정보가있는 테이블에 접속
@SequenceGenerator(       // 시퀀스 사용시 적용 -> JPA 관련된 어노테이션
        name = "SQ_FAQ_GENERATOR"
        , sequenceName = "SQ_FAQ"
        , initialValue = 1
        , allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Faq extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_FAQ_GENERATOR"  // 위의 name = "SQ_EMP_GENERATOR" 와 같음
    )
    private Integer fno;          // 사원번호(기본키, 시퀀스)

    private String title;         // 사원명

    private String content;           // 직위

}
