package me.shinsunyoung.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dto.AddUserRequest;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 사용자 관련 API를 처리하는 컨트롤러 클래스.
 * 회원 가입 기능을 담당하며, 회원 가입 후 로그인 페이지로 리다이렉트함.
 */
@RequiredArgsConstructor // Lombok을 사용하여 final 필드에 대한 생성자 자동 생성
@Controller // Spring MVC의 컨트롤러로 등록
public class UserApiController {

    private final UserService userService; // 사용자 서비스 (회원 가입 처리)

    /**
     * 회원 가입 요청을 처리하는 메서드.
     *
     * @param request 회원 가입 요청 데이터 (AddUserRequest DTO)
     * @return 로그인 페이지로 리다이렉트
     */
    @PostMapping("/user") // HTTP POST 요청을 "/user" 경로로 매핑
    public String signup(AddUserRequest request) {
        userService.save(request); // 사용자 정보 저장 (비밀번호 암호화 포함)

        return "redirect:/login"; // 회원 가입 후 로그인 페이지로 이동
    }

    /**
     * 로그아웃 요청을 처리하는 메서드.
     * 사용자가 "/logout" URL로 접근하면 Spring Security의 로그아웃 핸들러를 통해 로그아웃을 수행하고,
     * 로그인 페이지로 리다이렉트함.
     *
     * @param request  현재 HTTP 요청 객체
     * @param response 현재 HTTP 응답 객체
     * @return 로그아웃 후 로그인 페이지로 리다이렉트
     */
    @GetMapping("/logout") // HTTP GET 요청을 "/logout" 경로와 매핑
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // SecurityContextLogoutHandler를 사용하여 현재 사용자의 인증 정보 삭제 및 세션 무효화
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 이동
    }


}
