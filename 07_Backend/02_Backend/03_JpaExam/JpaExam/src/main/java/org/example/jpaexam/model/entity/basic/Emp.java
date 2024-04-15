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
    @Table(name = "TB_EMP")   // DB의 정보가있는 테이블에 접속
    @SequenceGenerator(       // 시퀀스 사용시 적용 -> JPA 관련된 어노테이션
            name = "SQ_EMP_GENERATOR"
            , sequenceName = "SQ_EMP"
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
    public class Emp extends BaseTimeEntity {
    //    연습 : Emp, EmpRepository, EmpService, EmpController, emp_all.jsp
    //       함수 : 전체 조회 : 페이징 없이 : findAll()
    //    대/소문자 바꾸기 단축키 : ctrl + shift + u(cmd + shift + u)

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE
                , generator = "SQ_EMP_GENERATOR"  // 위의 name = "SQ_EMP_GENERATOR" 와 같음
        )
        private Integer eno;          // 사원번호(기본키, 시퀀스)

        private String ename;         // 사원명

        private String job;           // 직위

        private Integer manager;      // 관리자사원번호

        private String hiredate;      // 입사일

        private Integer salary;       // 급여

        private Integer commission;   // 상여금

        private Integer dno;          // 부서번호
    }
