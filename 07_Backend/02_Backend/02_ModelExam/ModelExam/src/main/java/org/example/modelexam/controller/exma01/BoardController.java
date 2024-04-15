package org.example.modelexam.controller.exma01;

import lombok.extern.slf4j.Slf4j;
import org.example.modelexam.model.Board;
import org.example.modelexam.model.Dept;
import org.example.modelexam.service.exam01.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * packageName : org.example.modelexam.controller.exma01
 * fileName : BoardCrontroller
 * author : GGG
 * date : 2024-03-15
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-15         GGG          최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/exam01")
public class BoardController {

//    TODO: 전체조회 함수 불러오기
    @Autowired
    BoardService boardService;

    @GetMapping("/board")
    public String getBoardAll(Model model) {
//        TODO: Service 객체의 전체조회 함수를 호출
        List<Board> list = boardService.findAll();
//        TODO: jsp 전송(배열)
        model.addAttribute("list", list);

        return "exam01/board/board_all.jsp";
    }

//    TODO: 상세조회 함수 불러오기
    @GetMapping("/board/{id}")
    public String getDeptId(@PathVariable int id, Model model){

//        TODO: service 의 상세조회 함수(findById) 호출
        Board board = boardService.findById(id);
//        TODO: jsp로 board 객체 전송
        model.addAttribute("board",board);
        return "exam01/board/board_id.jsp";
    }

    //  todo: 연습 4-2)
//    BoardService 클래스를 만들고 save() 함수를 정의한다.
//    BoardController 클래스를 만들어서 addBoard(),
//    createBoard() 함수를 정의
//    addBoard()
//    - url : /board/additions
//    - jsp : exam01/board/add_board.jsp
//    createMember()
//    - url : /board/add
//    - redirect url : /exam01/board

//   TODO: 부서 추가 페이지 열기 함수
//    insert 사용하려면 controller 2개 필요함
    @GetMapping("/board/addition")
    public String addBoard() {
        return "exam01/board/add_board.jsp";
    }

    // TODO: 저장 버튼 클릭시 실행될 함수 #2
//   jsp : /exam01/dept  (저장버튼 클릭시 강제 페이지 이동:전체조회)
//   url : /exam01/dept/add
//   html : form 방식 : (insert -> post 방식)
//    어노테이션 : insert -> @PostMapping("/url")
    @PostMapping("/board/add")
    public RedirectView createBoard(@ModelAttribute Board board){

//    TODO: DB 저장 함수 실행
        boardService.save(board);

//    TODO: 전체 조회페이지로 강제 이동
//      RedirectView : 이 클래스가 강제 페이지 이동시켜주는 것
//          사용법 : new RedirectView("/공통url/함수url")
        return new RedirectView("/exam01/board");
    }

    //    TODO: 수정 페이지 열기 함수
//      => 수정 페이지가 열릴때 상세 조회한 결과를 수정함
//     url : /dept/edition/{dno}
//     jsp : exam01/dept/update_member.jsp
    @GetMapping("/board/edition/{id}")
    public String editDept(@PathVariable int id,
                           Model model) {
//      TODO: 1) 먼저 상세조회를(1건) 함
        Board board = boardService.findById(id);
//      TODO: 2) 부서 객체를 jsp 로 전송
        model.addAttribute("board", board);
        return "exam01/board/update_board.jsp";
    }

//    TODO: 수정 버튼 클릭시 실행될 함수
//     => 수정후 전체조회 페이지로 강제이동 :
//     => 사용법) new RedirectView("/이동할url")
//     => 클래스 == 자료형
//     => update => put 방식 => @PutMapping("/url")
//     => sql update ~ where 부서번호 = 10 -> 조건절 없으면 전체테이블 수정돼서 조건절 있어야함
//     => url : /dept/edit/{dno}
    @PutMapping("/board/edit/{id}")  // form 의 액션에 들어갈 url
    public RedirectView updateBoard(@PathVariable int id,
                                   @ModelAttribute Board board){
//        TODO: 수정 함수(서비스) 호출
//          => save : if (insert) else (update) : dno == null 이면 insert 되고 아니면 update 됨
//          => deptService 파일의 save 함수의 내용
        boardService.save(board);
//       TODO: 전체조회 페이지로 강제이동 : /exam01/dept
        return new RedirectView("/exam01/board");
    }


//   TODO:  삭제버튼 클릭시 실행될 함수
    @DeleteMapping("/board/delete/{id}")
    public RedirectView deleteBoard(@PathVariable int id){
//        TODO: DB 삭제 함수 실행
        boardService.removeById(id);
//        TODO: 삭제 후 전체조회 페이지로 강제 이동
        return new RedirectView("/exam01/board");
    }
}