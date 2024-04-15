package org.example.jpaexam.controller.basic;

import lombok.extern.slf4j.Slf4j;
import org.example.jpaexam.model.entity.basic.Dept;
import org.example.jpaexam.model.entity.basic.Emp;
import org.example.jpaexam.service.basic.EmpService;
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
 * fileName : EmpController
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
@Slf4j
@Controller
@RequestMapping("/basic")
public class EmpController {

    //    서비스 클래스 가져오기
    @Autowired
    EmpService empService;

//  전체조회
//  TODO: 페이징 복습 : page(현재페이지번호), size(1페이지당개수)
    @GetMapping("/emp")
    public String getEmpAll(
            @RequestParam(defaultValue = "") String ename,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
//        TODO: 페이징 매개변수 객체에 값넣기 : Pageable (page,size)
        Pageable pageable = PageRequest.of(page, size);
//        TODO: DB like 검색 서비스 함수 실행 : 페이징 처리
        Page<Emp> pageRes
                = empService
                .findAllByEnameContaining(ename, pageable);
//        Page 객체 : 굉장히 많은 불필요한 속성이 있음 : 현재페이지번호 등
//        자바 자료구조(컬렉션프레임워크) : List, Map<키, 값>
//        jsp 로 보낼 정보 : 1) 부서정보(배열)   2) 현재페이지번호
//                         3) 전체 테이블 건수 4) 전체 페이지 개수
//                         5) 블럭 시작페이지번호 6) 블럭 끝페이지번호

        model.addAttribute("emp",pageRes.getContent());              // 1) 부서정보(배열)
        model.addAttribute("currentPage",pageRes.getNumber());        // 2) 현재페이지번호
        model.addAttribute("totalItems",pageRes.getTotalElements());  // 3) 전체 테이블 건수
        model.addAttribute("totalPages",pageRes.getTotalPages());     // 4) 전체 페이지 개수

//      공식 : 블럭 시작페이지 번호 = (Math.floor(현재페이지번호/화면에보이는페이지번호개수)) * 1페이지당개수
//        TODO : 지금 한페이이당 3개 나오는데 이걸 늘리거나 줄이고 싶으면 size 를 조정하면됨
//                지금 위의 @RequestParam 으로 아무값도 안주면 3으로 돼있어 size 이렇게 줬을경우 3페이지까지 밖에 안나오지만
//                  직접 하드코딩하면 size 값만큼 페이지나옴
//        시작페이지 번호 있어야 반복문 돌려서 페이지 1 2 3 표시가능
        long blockStartPage = (long)Math.floor((double)(pageRes.getNumber())/size) * size; // 5) 블럭시작페이지 번호
        model.addAttribute("startPage", blockStartPage);

//      공식 : 블럭 끝페이지 번호 = 블럭 시작페이지번호 + 1페이자당개수 - 1
        long blockEndPage = blockStartPage + size - 1;
//        블럭 끝페이지 번호 != 전체페이지번호 : 이 경우가 발생할 수 있음 (계산하다보면 안맞을수 있음)
//        블럭 끝페이지 번호 = 전체페이지번호 - 1 (값 보정)
        blockEndPage = (blockEndPage >= pageRes.getTotalPages()?pageRes.getTotalPages()-1:blockEndPage);
        //        TODO: blockEndPage < 0 이면 blockEndPage = 0 고정 : blockEndPage 음수이면 jsp 반복문에서 에러발생(버그 수정)
        blockEndPage = (blockEndPage < 0)? 0 : blockEndPage;

        model.addAttribute("endPage",blockEndPage);

        return "basic/emp/emp_all.jsp";
    }

    //  상세조회 : 테스트용(생략)
    @GetMapping("/emp/{eno}")
    public String getEmpId(@PathVariable int eno,
                            Model model) {
//        DB 상세조회 서비스 함수 실행
        Optional<Emp> optionalEmp
                = empService.findById(eno);
//        JSP 객체 전송 : 사용법 : 옵셔널객체.get() : 객체 꺼내기
        model.addAttribute("emp", optionalEmp.get());
        return "basic/emp/emp_id.jsp";
    }

    //    저장 : 1) 추가(저장) 페이지 열기 함수
    @GetMapping("/emp/addtion")
    public String addEmp(){
        return "basic/emp/add_emp.jsp";
    }

//    2) 저장 버튼 클릭 시 실행될 insert 실행될 함수
//    insert -> post 방식 -> @PostMapping
//    저장 -> 전체조회 페이지 강제이동(리다이렉트)
//    변수 1개 전달 -> @PathVariable, @RequestParam
//    객체 1개 전달 -> @ModelAttribute
@PostMapping("/emp/add")
public RedirectView createEmp(
        @ModelAttribute Emp emp
){
//        DB 저장 서비스 함수 실행
    empService.save(emp);
//        전체 조회 페이지 강제이동(리다이렉트)
    return new RedirectView("/basic/emp");
}

    //    수정 : 1) 수정페이지 열기 함수 : 상세조회
    @GetMapping("/emp/edition/{eno}")
    public String editEmp(@PathVariable int eno, Model model){
//        서비스 상세조회 함수 호출 : return 값 : Opational
        Optional<Emp> optionalEmp = empService.findById(eno);
//        옵셔널 객체에서 꺼내서(부서객체) jsp 전송
        model.addAttribute("emp", optionalEmp.get());
        return "basic/emp/update_emp.jsp";
    }

    //    2) 수정 버튼 클릭시 실행될 함수
//    update -> put 방식 -> @PutMapping
    @PutMapping("/emp/edit/{eno}")                    // 상세조회는 객체 1개받음
    public RedirectView updateEmp(@PathVariable int eno, @ModelAttribute Emp emp){
//        DB 서비스 함수 실행 : save()
        empService.save(emp);
//        전체 조회 페치지로 강제 이동(리다이렉트)
        return new RedirectView("/basic/emp");
    }

    //    삭제 함수
//    delete -> delete 방식 -> @DeleteMapping
    @DeleteMapping("/emp/delete/{eno}")
    public RedirectView empDept(@PathVariable int eno){
//        DB 서비스 삭제 함수 실행
        empService.removeById(eno);
        return new RedirectView("/basic/emp");
    }
}
