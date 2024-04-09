package org.example.modelexam.service.exam01;

import org.example.modelexam.dao.BoardDao;
import org.example.modelexam.dao.FaqDao;
import org.example.modelexam.model.Board;
import org.example.modelexam.model.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.modelexam.service.exam01
 * fileName : FaqService
 * author : GGG
 * date : 2024-03-18
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-18         GGG          최초 생성
 */
@Service
public class FaqService {
//    TODO : 전체조회 함수
        @Autowired
        FaqDao faqDao;
        public List<Faq> findAll() {
//        DB 전체 조회
            List<Faq> list = faqDao.selectAll();

            return list;
        }

//  TODO : 상세조회 함수
    public Faq findById(int id) {
//        TODO: DB 상세조건(1건==객체1개) 함수 호출

        Faq faq = faqDao.selectById(id);

        return faq;
    }

//    TODO: 저장함수 정의
    public List<Faq> save(Faq faq) {

        List<Faq> list = null;
        if (faq.getFno() == null) {
//      게시판번호가 없으면 (null) : insert
//      게시판번호가 있으면        : update
            list = faqDao.insert(faq);
        } else {
//            TODO: 부서 수정(update)
            list = faqDao.update(faq);
        }
        return list;
    }

    //     TODO: 삭제함수 정의
    public boolean removeById(int fno) {
//        TODO: DB 삭제 함수 실행
//         => 삭제후 return 값은 삭제한 개수가 나옴
        int count = faqDao.deleteById(fno);

//    count 가 0 보다 크면 1건 이상 삭제한거니 삭제가 됬는지 확인하는 3항연산자(if) 내보냄
        return (count > 0) ? true : false;
    }
}
