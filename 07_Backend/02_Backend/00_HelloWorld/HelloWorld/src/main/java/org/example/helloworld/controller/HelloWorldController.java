package org.example.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : org.example.helloworld.controller
 * fileName : HelloWorldController
 * author : GGG
 * date : 2024-03-11
 * description :
 * 요약 :
 *      springboot 는 어노테이션(@문자열) 기반 코딩을 함
 *      어노테이션 : 함수와 비슷, 자주쓰는 기능을 구현해 놓은 표현식
 *                  클래스 , 함수 , 변수 등에 붙일 수 있음
 *      @Controller : 클래스에 컨트롤러 기능을 부여하는 어노테이션
 *      @GetMapping : 함수에 url 을 추가하는 어노테이션, 웹브라우저에 url 로 결과를 확인가능
 *          vue 의 라우팅 기능과 유사, 결과는 jsp 의 결과가 보임
 *              - return 문자열 : jsp 파일 위치 예) /exam00/hello.jsp
 *          예) 웹브라우저 주소창 : http//localhost:8000/함수 url => 결과 확인
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-11         GGG          최초 생성
 */
@Controller
public class HelloWorldController {

    //    함수(메소드)
    @GetMapping("/exam00/hello")
    public String Hello(Model model) {
        return "/exam00/hello.jsp";
    }
}
