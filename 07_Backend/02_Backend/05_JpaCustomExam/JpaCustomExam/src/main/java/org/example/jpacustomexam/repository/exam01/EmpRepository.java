package org.example.jpacustomexam.repository.exam01;

import org.example.jpacustomexam.model.dto.EmpGroupDto;
import org.example.jpacustomexam.model.entity.exam01.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName : org.example.jpacustomexam.repository.exam01
 * fileName : EmpRepository
 * author : GGG
 * date : 2024-03-29
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29         GGG          최초 생성
 */
public interface EmpRepository extends JpaRepository<Emp, Integer> {
    //    연습 : 1) @Query 사용 : 오라클 sql 사용
//     Emp 테이블에서 salary 내림차순, ename 오름차순으로 정렬해서 전체조회 하세요
    @Query(value = "SELECT E.* FROM TB_EMP E\n" +
            "ORDER BY E.SALARY DESC, E.ENAME ASC"
            , nativeQuery = true)
    List<Emp> selectBySalaryAndEname(
    );

    //    연습 : 2) @Query 사용 : 오라클 sql 사용
//           commission 이 null 이고
////         salary 값보다 같거나 큰 사원 정보를 모두 출력하세요.
    @Query(value = "SELECT E.* FROM TB_EMP E\n" +
            "WHERE COMMISSION IS NULL\n" +
            "AND   SALARY >= :salary"
            , nativeQuery = true)
    List<Emp> selectByCommissionAndSalary(
            @Param("salary") int salary
    );

    //    연습 3) dno, job 별 월급여의 합을 출력하는 함수를 작성하세요.(tb_emp)
////      nativeQuery = true 사용,
//        dto : EmpGroupDto
    @Query(value = "SELECT DNO, JOB, SUM(SALARY) AS salary\n" +
            "    FROM TB_EMP\n" +
            "    GROUP BY DNO,JOB "
            , nativeQuery = true)
    List<EmpGroupDto> selectGroupDnoJob(
    );
}
