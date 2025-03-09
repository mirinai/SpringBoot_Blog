package me.shinsunyoung.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 사용자 화면(View) 관련 요청을 처리하는 컨트롤러 클래스.
 * 로그인 및 회원가입 페이지를 반환하는 역할을 수행함.
 */
@Controller // Spring MVC의 컨트롤러로 등록
public class UserViewController {

    /**
     * 로그인 페이지 요청을 처리하는 메서드.
     * 사용자가 "/login" URL로 접속하면 login.html 뷰를 반환함.
     *
     * @return 로그인 페이지 (login.html)
     */
    @GetMapping("/login") // HTTP GET 요청을 "/login" 경로와 매핑
    public String login() {
        return "login"; // login.html을 반환 (templates/login.html)
    }

    /**
     * 회원가입 페이지 요청을 처리하는 메서드.
     * 사용자가 "/signup" URL로 접속하면 signup.html 뷰를 반환함.
     *
     * @return 회원가입 페이지 (signup.html)
     */
    @GetMapping("/signup") // HTTP GET 요청을 "/signup" 경로와 매핑
    public String signup() {
        return "signup"; // signup.html을 반환 (templates/signup.html)
    }
}
