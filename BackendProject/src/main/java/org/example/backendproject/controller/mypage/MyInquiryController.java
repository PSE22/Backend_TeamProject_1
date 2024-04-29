package org.example.backendproject.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.example.backendproject.model.dto.shop.IQnaDto;
import org.example.backendproject.service.shop.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : org.example.backendproject.controller.mypage
 * fileName : MyInquiryController
 * author : gumiji
 * date : 4/28/24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 4/28/24         gumiji          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyInquiryController {
    @Autowired
    QnaService qnaService;

    //    TODO: 전체 조회 함수 + like 검색
    @GetMapping("/inquiry")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") int pdQnaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<IQnaDto> qnaDtoPage
                    = qnaService.selectByQnaContaining(pdQnaId,pageable);

//            공통 페이징 객체 생성 : 자료 구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("qna", qnaDtoPage.getContent()); // qna 배열
            response.put("currentPage", qnaDtoPage.getNumber()); // 현재페이지번호
            response.put("totalItems", qnaDtoPage.getTotalElements()); // 총건수(개수)
            response.put("totalPages", qnaDtoPage.getTotalPages()); // 총페이지수

            if (qnaDtoPage.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
