package org.example.jpaexam.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.model.entity.basic.Faq;
import org.example.jpaexam.service.basic.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.jpaexam.controller.basic
 * fileName : FaqController
 * author : GGG
 * date : 2024-03-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-21         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/basic")
public class FaqController {
    //    서비스 클래스 가져오기
    @Autowired
    FaqService faqService;


    //  전체조회
//  TODO: 페이징
    @GetMapping("/faq")
    public String getFaqAll(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Faq> pageRes
                = faqService
                .findAllByTitleContaining(title, pageable);

        model.addAttribute("faq", pageRes.getContent());              // 1) 부서정보( 배열)
        model.addAttribute("currentPage", pageRes.getNumber());        // 2) 현재페이지번호
        model.addAttribute("totalItems", pageRes.getTotalElements());  // 3) 전체 테이블 건수
        model.addAttribute("totalPages", pageRes.getTotalPages());     // 4) 전체 페이지 개수

        long blockStartPage = (long) Math.floor((double) (pageRes.getNumber()) / size) * size; // 5) 블럭시작페이지 번호
        model.addAttribute("startPage", blockStartPage);

        long blockEndPage = blockStartPage + size - 1;
        blockEndPage = (blockEndPage >= pageRes.getTotalPages() ? pageRes.getTotalPages() - 1 : blockEndPage);
        blockEndPage = (blockEndPage < 0) ? 0 : blockEndPage;

        model.addAttribute("endPage", blockEndPage);

        return "basic/faq/faq_all.jsp";
    }

    //  상세조회 : 테스트용(생략)
    @GetMapping("/faq/{fno}")
    public String getFaqId(@PathVariable int fno,
                           Model model) {
//        DB 상세조회 서비스 함수 실행
        Optional<Faq> optionalFaq
                = faqService.findById(fno);
//        JSP 객체 전송 : 사용법 : 옵셔널객체.get() : 객체 꺼내기
        model.addAttribute("faq", optionalFaq.get());
        return "basic/faq/faq_id.jsp";
    }

    //    저장 : 1) 추가(저장) 페이지 열기 함수
    @GetMapping("/faq/addtion")
    public String addFaq(){
        return "basic/faq/add_faq.jsp";
    }

    //    2) 저장 버튼 클릭 시 실행될 insert 실행될 함수
    @PostMapping("/faq/add")
    public RedirectView createFaq(
            @ModelAttribute Faq faq
    ){
//        DB 저장 서비스 함수 실행
        faqService.save(faq);
//        전체 조회 페이지 강제이동(리다이렉트)
        return new RedirectView("/basic/faq");
    }

    //    수정 : 1) 수정페이지 열기 함수 : 상세조회
    @GetMapping("/faq/edition/{fno}")
    public String editFaq(@PathVariable int fno, Model model){
//        서비스 상세조회 함수 호출 : return 값 : Opational
        Optional<Faq> optionalFaq = faqService.findById(fno);
//        옵셔널 객체에서 꺼내서(부서객체) jsp 전송
        model.addAttribute("faq", optionalFaq.get());
        return "basic/faq/update_faq.jsp";
    }

    //    2) 수정 버튼 클릭시 실행될 함수
//    update -> put 방식 -> @PutMapping
    @PutMapping("/faq/edit/{fno}")                    // 상세조회는 객체 1개받음
    public RedirectView updateFaq(@PathVariable int fno, @ModelAttribute Faq faq){
//        DB의 수정 서비스 함수 실행 : save()
        faqService.save(faq);
//        전체 조회 페치지로 강제 이동(리다이렉트)
        return new RedirectView("/basic/faq");
    }

    //    삭제 함수
//    delete -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/faq/delete/{fno}")
    public RedirectView empFaq(@PathVariable int fno){
//        DB 서비스 삭제 함수 실행
        faqService.removeById(fno);
        return new RedirectView("/basic/faq");
    }
}
