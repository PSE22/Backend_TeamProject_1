package org.example.controllerexam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : org.example.controllerexam.model
 * fileName : Member
 * author : GGG
 * date : 2024-03-12
 * description : 모델(Model) 클래스 : 회원
 * 요약 :
 *      TODO: Model : 정보를 저장/수정/삭제/추가 등을 하는 클래스
 *       => 대상 : 속성(필드)에 값을 저장/수정/삭제/추가
 *       => 속성(필드), 생성자, Setter/Getter 함수 : 이것만 정의함 (모델 클래스)
 *
 *      TODO: 롬북(lombok) 패키지의 어노테이션 정의
 *          @Setter             : setter 함수를 만들어주는 어노테이션
 *          @Getter             : getter 함수를 만들어주는 어노테이션
 *          @AllArgsConstructor : 모든 속성(필드)을 가진 생성자를 만들어주는 어노테이션
 *          @ToString           : toString 함수를 재정의해주는 어노테이션
 *          => 롬북 어노테이션 또는 다른 어노테이션의 붙이는 순서는 상관없음
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-12         GGG          최초 생성
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Member {
    //    클래스의 3요소 : 속성(필드) , 생성자, 함수(메소드)
    String id;      // 회원 id
    String name;    // 회원명

//    TODO:    Controller 객체에서 @ModelAttribute 어트리 뷰션을 사용한다음 값을 지금 이 클래스의 이름인
//              Member member 이렇게 넣은다음 jsp와 연결한다 그후 jsp에서 앞에서 설정한 member 로 전달받아서
//              Member 클래스의 속성,생성자,함수를 사용할수 있음
//              그래서 앞에서 했던것처럼 Model model 한다음 model.Attribute(키,내용) 할필요 없이 바로
//               Member 클래스의 작성물을 바로 사용할수 있음

//    TODO: 원래 @ToString, @Setter, @Getter 등을 alt + insert 해가면서 직접 추가 해야했는데
//              이렇게 할경우 코딩이 길어지고 복잡해진다
//               그래서 그에 해당하는 어노테이션을 삽입하면 직접 추가할필요 import 되며 대체된다





//    TODO: 생성자 : 단축키 : alt + insert (cmd + n)
//     => 어노테이션으로 대체 : 1) 클래스 위에 붙임 : @AllArgsConstructor

//    TODO: Setter(저장)/Getter(검색/조회) : alt + insert (cmd + n)
//     => 어노테이션으로 대체 : 1) 클래스 위에 붙임 : @Setter, @Getter

//    TODO: toString 재정의 : 클래스 화면에 출력하면 속성값이 출력안되고
//       메모리 방번호(주소값)가 출력됨 : alt + insert (cmd + n)
//     => 어노테이션으로 대체 : 1) 클래스 위에 붙임 : @ToString
}
