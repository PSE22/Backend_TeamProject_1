package org.example.jpaexam.repository.basic;

import org.example.jpaexam.model.entity.basic.Dept;
import org.example.jpaexam.model.entity.basic.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.jpaexam.repository.basic
 * fileName : EmpRepositoy
 * author : GGG
 * date : 2024-03-19
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-19         GGG          최초 생성
 */
//  TODO: 연습 2) empRepository 에 findAllByEnameContaining() like 검색 함수 제작
//    empService 에 findAllByEnameContaining() 함수 제작
//    empController 의 getEmpAll() 함수에 페이징 처리 로직 추가
//    emp_all.jsp 페이징 화면 추가(테이블로 출력)
//    참고 : 부서 참고
@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
//    like 검색 함수 : JPA sql 직접 장석 : @Query(value ="",)
//    사용법 : @Query(value="sql 문",  countQuery ="sql 문2" nativeQuery=true)

//    @Query(value = "SELECT * FROM TB_EMP E\n" +
//            "WHERE ENAME LIKE '%S%'") 로 Oracle 에서 한번 체크해보기
//    '%S%' 는 하드코딩으로 체크한것이고 소프트코딩으로 ename 으로 넣어줘야 하는데
//    그러기 위해서는 @Page 부분에 @Param("ename") 으로 작성해줘야함
    @Query(value =  "SELECT * FROM TB_EMP E\n" +
            "WHERE E.ENAME LIKE '%'|| :ename ||'%'"
            ,countQuery = "SELECT count(*) FROM TB_Emp E\n" +
            "WHERE E.ENAME LIKE '%'|| :ename || '%'"
            , nativeQuery = true)
//    페이징 처리하기 위해 Page로 return 하기
    Page<Emp> findAllByEnameContaining(
            @Param("ename") String ename,
            Pageable pageable
    );
}
