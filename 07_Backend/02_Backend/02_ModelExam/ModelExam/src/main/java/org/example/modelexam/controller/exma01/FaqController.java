package org.example.modelexam.controller.exma01;

import lombok.extern.slf4j.Slf4j;
import org.example.modelexam.model.Board;
import org.example.modelexam.model.Faq;
import org.example.modelexam.service.exam01.BoardService;
import org.example.modelexam.service.exam01.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * packageName : org.example.modelexam.controller.exma01
 * fileName : FaqController
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
@Slf4j
@Controller
@RequestMapping("/exam01")
public class FaqController {
    //    TODO: 전체조회 함수 불러오기
    @Autowired
    FaqService faqService;

    @GetMapping("/faq")
    public String getFaqAll(Model model) {

        List<Faq> list = faqService.findAll();

        model.addAttribute("list", list);

        return "exam01/faq/faq_all.jsp";
    }

    //    TODO: 상세조회 함수 불러오기
    @GetMapping("/faq/{id}")
    public String getFaqId(@PathVariable int id, Model model){

        Faq faq = faqService.findById(id);

        model.addAttribute("faq",faq);
        return "exam01/faq/faq_id.jsp";
    }


//   TODO: 부서 추가 페이지 열기 함수
//   TODO: 저장 버튼 클릭시 실행될 함수 #1
//    insert 사용하려면 controller 2개 필요함
    @GetMapping("/faq/addition")
    public String addBoard() {
        return "exam01/faq/add_faq.jsp";
    }

    // TODO: 저장 버튼 클릭시 실행될 함수 #2
    @PostMapping("/faq/add")
    public RedirectView createFaq(@ModelAttribute Faq faq){

//    TODO: DB 저장 함수 실행
        faqService.save(faq);

//    TODO: 전체 조회페이지로 강제 이동
        return new RedirectView("/exam01/faq");
    }


    //    TODO: 수정 페이지 열기 함수
//      => 수정 페이지가 열릴때 상세 조회한 결과를 수정함
//     url : /dept/edition/{dno}
//     jsp : exam01/dept/update_member.jsp
    @GetMapping("/faq/edition/{fno}")
    public String editFno(@PathVariable int fno,
                           Model model) {
//      TODO: 1) 먼저 상세조회를(1건) 함
        Faq faq = faqService.findById(fno);
//      TODO: 2) 부서 객체를 jsp 로 전송
        model.addAttribute("faq", faq);
        return "exam01/faq/update_faq.jsp";
    }

//    TODO: 수정버튼 클릭시 실행될 함수
    @PutMapping("/faq/edit/{fno}")  // form 의 액션에 들어갈 url
    public RedirectView updateFaq(@PathVariable int fno,
                                    @ModelAttribute Faq faq){
//        TODO: 수정 함수(서비스) 호출
//          => save : if (insert) else (update) : dno == null 이면 insert 되고 아니면 update 됨
//          => deptService 파일의 save 함수의 내용
        faqService.save(faq);
//       TODO: 전체조회 페이지로 강제이동 : /exam01/dept
        return new RedirectView("/exam01/faq");
    }


    //   TODO:  삭제버튼 클릭시 실행될 함수
    @DeleteMapping("/faq/delete/{fno}")
    public RedirectView deleteFaq(@PathVariable int fno){
//        TODO: DB 삭제 함수 실행
        faqService.removeById(fno);
//        TODO: 삭제 후 전체조회 페이지로 강제 이동
        return new RedirectView("/exam01/faq");
    }
}
