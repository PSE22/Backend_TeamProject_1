package org.example.jpaexam.service.basic;

import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.model.entity.basic.Faq;
import org.example.jpaexam.repository.basic.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.service.basic
 * fileName : FaqServiCe
 * author : GGG
 * date : 2024-03-21
 * description :
 * 요약 :
 *      IOC : 제어의 역전
 *            -> 스프링이 객체를 생성해줌
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21         GGG          최초 생성
 */
@Service
public class FaqService {

    //    DB JPA CRUD 클래스 받기 : DI(의존성 주입) -> 함수 사용가능
    @Autowired
    FaqRepository faqRepository;

    //  TODO: 전체조회 함수
    public List<Faq> findAll(){
//        DB JPA 전체조회 함수 호출 : JPA 자동 sql 문 작성함
        List<Faq> list = faqRepository.findAll();
        return list;
    }

    public Page<Faq> findAllByTitleContaining(String title, Pageable pageable) {
//        DB LIKE 검색 함수 실행 : 페이징 처리
        Page<Faq> page = faqRepository.findAllByTitleContaining(title, pageable);

        return page;
    }

    public Optional<Faq> findById(int fno) {
//        JPA 상세조회 함수 실행
        Optional<Faq> optionalFaq
                = faqRepository.findById(fno);
        return optionalFaq;
    }

    //    저장/수정 : 1) 기본키가 없으면 저장(insert)
//               2) 기본키가 있으면 수정(update)
//            => JPA 내부적으로 if문 있음 : 알아서 실행됨
    public Faq save(Faq faq) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Faq faq2 = faqRepository.save(faq);

        return faq2;
    }

    public boolean removeById(int fno) {

//        JPA 삭제함수 : deleteById(기본키) -> 모두 삭제안하고 기본키만 삭제
//        1) 먼저 기본키가 테이블에 있으면 삭제 -> ture 리턴
//            없으면 삭제안하고 false 리턴
//        JPA 삭제함수 사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있는지 확인하고 있으면 true, 없으면 false
        if (faqRepository.existsById(fno)) {   // JPA 기본함수 : existsById -> 기본키가 있나없나 확인하는 함수
            faqRepository.deleteById(fno);
            return true;
        } else {
            return false;
        }
    }
}
