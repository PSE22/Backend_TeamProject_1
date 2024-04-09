package org.example.modelexam.service.exam01;

import org.example.modelexam.dao.BoardDao;
import org.example.modelexam.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * packageName : org.example.modelexam.service.exam01
 * fileName : BoardService
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
@Service
public class BoardService {
    //  todo: 연습 2)
//    BoardService 클래스를 만들고 findAll() 함수를 정의한다.
//    BoardController 클래스를 만들어서 getBoardAll() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : board_all.jsp) 전송해 보세요.
//    DB 접속하는(조회/수정/추가/삭제) 클래스 : BoardDao
//    TODO : 전체조회 함수
    @Autowired
    BoardDao boardDao;

    public List<Board> findAll() {
//        DB 전체 조회
        List<Board> list = boardDao.selectAll();

        return list;
    }

    //  todo: 연습 2-2)
//    BoardService 클래스를 만들고 findById() 함수를 정의한다.
//    BoardController 클래스를 만들어서 getBoardId() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : board_id.jsp) 전송해 보세요.
//    url : /board/{id}
//    jsp : exam01/board/board_id.jsp
//    url test : http://localhost:8000/exam01/board/1
//  TODO : 상세조회 함수

    public Board findById(int id) {
//        TODO: DB 상세조건(1건==객체1개) 함수 호출

        Board board = boardDao.selectById(id);

        return board;
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
    public List<Board> save(Board board) {

        List<Board> list = null;
        if (board.getId() == null) {
//      게시판번호가 없으면 (null) : insert
//      게시판번호가 있으면        : update
            list = boardDao.insert(board);
        } else {
//            TODO: 부서 수정(update)
            list = boardDao.update(board);
        }
        return list;

    }

    //  todo: 연습 6-2)
//    BoardService 클래스를 만들고 removeById() 함수를 정의한다.
//    BoardController 클래스를 만들어서 deleteBoard() 함수를 정의
//    update_board.jsp 삭제버튼과 url 를 추가한다.
//    deleteBoard()
//    - url : /board/delete/{id}
//    - redirect url : /exam01/board
    public boolean removeById(int id) {
//        TODO: DB 삭제 함수 실행
//         => 삭제후 return 값은 삭제한 개수가 나옴
        int count = boardDao.deleteById(id);

//    count 가 0 보다 크면 1건 이상 삭제한거니 삭제가 됬는지 확인하는 3항연산자(if) 내보냄
        return (count > 0) ? true : false;
    }
}
