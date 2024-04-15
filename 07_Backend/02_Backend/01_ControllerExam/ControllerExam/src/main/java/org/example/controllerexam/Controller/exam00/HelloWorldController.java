package org.example.controllerexam.Controller.exam00;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : org.example.controllerexam.Controller.exam00
 * fileName : HelloWorldController
 * author : GGG
 * date : 2024-03-11
 * description :
 * 요약 :
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
    public String Hello(Model model) {return "/exam00/hello.jsp";}

    @GetMapping("/exam00/hello2")
    public String Hello2(Model model) {return "/exam00/hello2.jsp";}
}


