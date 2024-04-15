package org.example.modelexam.service.exam01;

import org.example.modelexam.dao.MemberDao;
import org.example.modelexam.model.Dept;
import org.example.modelexam.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName : org.example.modelexam.service.exam01
 * fileName : MmebeService
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
@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;  // DB 조회/수정/삭제/추가 함수 있는 클래스

    /**
     * 전체 조회 : 멤버 클래스
     *
     * @return 멤버 배열
     */
    public List<Member> findAll() {
//        TODO : 멤버 전체 조회
        List<Member> list = memberDao.selectAll();

        return list;
    }

    //  todo: 연습 2)
//    MemberService 클래스를 만들고 findById() 함수를 정의한다.
//    MemberController 클래스를 만들어서 getMemberId() 함수를 정의하고,
//    샘플데이터 뷰로(jsp : member_id.jsp) 전송해 보세요.
//    url : /member/{eno}
//    jsp : exam01/member/member_id.jsp
//    url test : http://localhost:8000/exam01/member/7369
    public Member findById(long eno) {
//        TODO: DB 상세조건(1건==객체1개) 함수 호출
//              상세조회 : 기본키로 조회하는 것을 의미 (기본키 = dno)
        Member member = memberDao.selectById(eno);

        return member;
    }

    //  todo: 연습 3)
//    MemberService 클래스를 만들고 save() 함수를 정의한다.
//    MemberController 클래스를 만들어서 addMember(),
//    createMember() 함수를 정의
//    addMember()
//    - url : /member/addition
//    - jsp : exam01/member/add_member.jsp
        public List<Member> save(Member member) {
//           - deptDao.insert(dept);
            List<Member> list = null;
            if(member.getEno() == null) {
//         TODO: 부서 추가(insert)
//        1) dept 객체의 dno 가 null 이면 자동생성
//        2) dept 부서 테이블의 전체 건수 구한후 : 4건(10 ~ 40)
//            List/ArrayList 배열크기 함수 : .size()
                int count = memberDao.selectAll().size();
//        3) 새로운 번호 : (크기 + 1) * 10
                int newNum = (count + 1) * 10;
//        4) dept 의 setter 함수(속성 수정/저장함수)
                member.setEno(newNum);
                //      TODO: DB 저장함수 (insert 함수)
                list = memberDao.insert(member);
            }
            else {
//            TODO: 부서 수정(update)
                list = memberDao.update(member);
            }
            return list;
        }


    public boolean removeById(int eno){
//        TODO: DB 삭제 함수 실행
//         => 삭제후 return 값은 삭제한 개수가 나옴
        int count = memberDao.deleteById(eno);

//    count 가 0 보다 크면 1건 이상 삭제한거니 삭제가 됬는지 확인하는 3항연산자(if) 내보냄
        return (count > 0)? true : false;

    }
}



