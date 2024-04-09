package org.example.jpacustomexam.model.entity.exam03;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.jpacustomexam.model.common.BaseTimeEntity;
import org.example.jpacustomexam.model.entity.exam02.Dept2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.jpacustomexam.model.entity.exam03
 * fileName : Employee
 * author : GGG
 * date : 2024-04-01
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-01         GGG          최초 생성
 */
@Entity
@Table(name = "TB_EMPLOYEE")
@SequenceGenerator(
        name = "SQ_EMPLOYEE_GENERATOR"
        , sequenceName = "SQ_EMPLOYEE"
        , initialValue = 1
        , allocationSize = 1
)
@Setter
@Getter
@ToString(exclude = "department") // exclude : 제외 -> 순환참조 방지
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Employee extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_EMPLOYEE_GENERATOR"
    )
    private Integer eno;         // 기본키, 시퀀스

    private String ename;

    private String job;

    private Integer manager;

    private String hiredate;

    private Integer salary;

    private Integer commission;

    //    private Integer dno;        // 이퀄 조인 컬럼
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dno")
    @JsonBackReference
    private Department department;           // 조인 부모 클래스 속성(필드)
}
