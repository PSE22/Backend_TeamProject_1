package org.example.backend.service.shop;

import org.example.backend.model.dto.shop.IQnaDto;
import org.example.backend.model.entity.PdQna;
import org.example.backend.repository.shop.QnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.backendproject.service.shop
 * fileName : QnaService
 * author : sjuk2
 * date : 2024-04-24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24         sjuk2          최초 생성
 */
@Service
public class QnaService {
    @Autowired
    QnaRepository qnaRepository;

//    TODO: 저장(수정) 함수
    public PdQna save(PdQna pdQna) {
//        DB 저장 함수
        PdQna pdQna2 = qnaRepository.save(pdQna);
        return pdQna2;
    }

//    TODO: 전체 조회 함수
    public Page<IQnaDto> selectByQnaContaining(
            Long pdId,
            Pageable pageable
    ) {
        Page<IQnaDto> page = qnaRepository.selectByQnaContaining(pdId, pageable);
        return page;
    }
}
