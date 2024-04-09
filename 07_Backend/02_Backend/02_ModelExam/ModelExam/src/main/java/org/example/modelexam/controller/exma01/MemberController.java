package org.example.modelexam.controller.exma01;

import org.example.modelexam.model.Dept;
import org.example.modelexam.model.Member;
import org.example.modelexam.service.exam01.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * packageName : org.example.modelexam.controller.exma01
 * fileName : MemberController
 * author : GGG
 * date : 2024-03-13
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-13         GGG          최초 생성
 */
//    연습 1:
//    MemberService 클래스를 만들고 findAll() 함수를 정의한다.
//     ( MemberDao : DB 조회/수정/삭제/추가 함수가 있는 클래스 )
//    MemberController 클래스를 만들어서 getMemberAll() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : member_all.jsp) 전송해 보세요.
//    url : member
//    jsp : exam01/member/member_all.jsp
    @Controller
    @RequestMapping("exam01")
public class MemberController {

//   TODO: service 객체 가져오기
    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public String getNumberAll(Model model) {

//        TODO: Service 객체의 전체조회 함수(findAll)를 호출
        List<Member> list = memberService.findAll();
//        TODO: jsp 전송(배열)
        model.addAttribute("list", list);

        return "exam01/member/member_all.jsp";


        }

//  TODO : 연습 2)
//    MemberService 클래스를 만들고 findById() 함수를 정의한다.
//    MemberController 클래스를 만들어서 getMemberId() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : member_id.jsp) 전송해 보세요.
//    url : /member/{eno}
//    jsp : exam01/member/member_id.jsp
//    url test : http://localhost:8000/exam01/member/7369
    @GetMapping("/member/{eno}")
    public String getMemberId(@PathVariable long eno, Model model){

//      TODO: Service 상세조회 함수(findById) 호출
        Member member = memberService.findById(eno);
//      TODO: member 객체 jsp 로 전달
        model.addAttribute("member",member);
        return "exam01/member/member_id.jsp";
    }

// todo: 연습 3)
//    MemberService 클래스를 만들고 save() 함수를 정의한다.
//    MemberController 클래스를 만들어서 addMember(),
//    createMember() 함수를 정의
//    addMember()
//    - url : /member/addition
//    - jsp : exam01/member/add_member.jsp

//    TODO: 새로운 멤버 추가 페이지 열기 함수
@GetMapping("/member/addition")
public String addMember(){
    return "exam01/member/add_member.jsp";
}

//   TODO: 저장 버튼 클릭시 실행될 함수
//    createMember()
//    - url : /member/add
//    - redirect url : /exam01/member
//    어노테이션 : insert -> @PostMapping("/url")
//    - insert (추가) @PostMapping
//    Member 객체의속성 : eno(정수) , ename(문자열), job(문자열), manager(정수)
@PostMapping("/member/add")
public RedirectView createMember(@ModelAttribute Member member){

//    TODO: DB 저장 함수 실행
    memberService.save(member);

//    TODO: 전체 조회페이지로 강제 이동
//      RedirectView : 이 클래스가 강제 페이지 이동시켜주는 것
//          사용법 : new RedirectView("/공통url/함수url")
    return new RedirectView("/exam01/member");
}

//  todo: 연습 5)
//    MemberService 클래스를 만들고 save() 함수를 정의하고 update 하세요
//    MemberController 클래스를 만들어서 editMember() 함수를 정의
//    editMember()
//    - url : /member/edition/{eno}
//    - jsp : exam01/member/update_member.jsp
//    TODO: 수정 페이지 열기 함수
//      => 수정 페이지가 열릴때 상세 조회한 결과를 수정함
//     실행 url : http://localhost:8000/exam01/member/edition/7369
@GetMapping("/member/edition/{eno}")
public String editMember(@PathVariable long eno,
                       Model model) {
//        TODO: 1) 사원번호로(eno) 상세조회
    Member member = memberService.findById(eno);
//        TODO: 2) 사원 객체 를 jsp 로 전달
    model.addAttribute("member", member);
    return "exam01/member/update_member.jsp";
}

    @PutMapping("/member/edit/{eno}")  // form 의 액션에 들어갈 url
    public RedirectView updateMember(@PathVariable long eno,
                                   @ModelAttribute Member member){
//        TODO : DB 수정(서비스함수) 실행
        memberService.save(member);
//       TODO: 전체조회 페이지로 강제이동 : /exam01/member
        return new RedirectView("/exam01/member");
    }


    @DeleteMapping("/member/delete/{eno}")
    public RedirectView deleteMember(@PathVariable int eno){
//        TODO: DB 삭제 함수 실행
        memberService.removeById(eno);
//        TODO: 삭제 후 전체조회 페이지로 강제 이동
        return new RedirectView("/exam01/member");
    }
}

