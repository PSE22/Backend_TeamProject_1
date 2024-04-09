package org.example.jpaexam.service.basic;

import org.example.jpaexam.model.entity.basic.Dept;
import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.repository.basic.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : EmpService
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
@Service
public class EmpService {

//    DB JPA CRUD 클래스 받기 : DI(의존성 주입) -> 함수 사용가능
    @Autowired
    EmpRepository empRepository;

//  TODO: 전체조회 함수
    public List<Emp> findAll(){
//        DB JPA 전체조회 함수 호출 : JPA 자동 sql 문 작성함
        List<Emp> list = empRepository.findAll();
        return list;
    }

    //  like 검색 함수 : 페이징 처리
    public Page<Emp> findAllByEnameContaining(String ename, Pageable pageable) {
//        DB LIKE 검색 함수 실행 : 페이징 처리
        Page<Emp> page = empRepository.findAllByEnameContaining(ename, pageable);

        return page;
    }

    public Optional<Emp> findById(int eno) {
//        JPA 상세조회 함수 실행
        Optional<Emp> optionalEmp
                = empRepository.findById(eno);
        return optionalEmp;
    }

    //    저장/수정 : 1) 기본키가 없으면 저장(insert)
//               2) 기본키가 있으면 수정(update)
//            => JPA 내부적으로 if문 있음 : 알아서 실행됨
    public Emp save(Emp emp) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Emp emp2 = empRepository.save(emp);

        return emp2;
    }

    public boolean removeById(int eno) {

//        JPA 삭제함수 : deleteById(기본키) -> 모두 삭제안하고 기본키만 삭제
//        1) 먼저 기본키가 테이블에 있으면 삭제 -> ture 리턴
//            없으면 삭제안하고 false 리턴
//        JPA 삭제함수 사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있는지 확인하고 있으면 true, 없으면 false
        if (empRepository.existsById(eno)) {   // JPA 기본함수 : existsById -> 기본키가 있나없나 확인하는 함수
            empRepository.deleteById(eno);
            return true;
        } else {
            return false;
        }
    }
}
