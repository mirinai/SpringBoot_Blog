package me.shinsunyoung.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

/**
 * Spring Security의 보안 설정을 담당하는 클래스.
 * 사용자 인증 및 권한 관리를 설정하며, 특정 URL에 대한 접근을 제어함.
 */
@Configuration // Spring 설정 클래스임을 나타냄
@EnableWebSecurity // Spring Security 활성화
@RequiredArgsConstructor // Lombok을 사용하여 final 필드에 대한 생성자 자동 생성
public class WebSecurityConfig {

    private final UserDetailService userService; // 사용자 정보를 제공하는 서비스

    /**
     * 정적 리소스 및 H2 콘솔에 대한 보안 예외 처리 설정.
     * 해당 요청은 Spring Security의 보안 필터를 거치지 않도록 설정함.
     *
     * @return WebSecurityCustomizer 객체
     */
    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring()
                .requestMatchers(toH2Console()) // H2 콘솔 접근 허용
                .requestMatchers(new AntPathRequestMatcher("/static/**")); // 정적 리소스 접근 허용
    }

    /**
     * SecurityFilterChain을 설정하여 HTTP 보안 정책을 정의하는 메서드.
     *
     * @param http HttpSecurity 객체
     * @return 설정이 적용된 SecurityFilterChain 객체
     * @throws Exception 설정 과정에서 발생할 수 있는 예외
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/login"), // 로그인 페이지 접근 허용
                                new AntPathRequestMatcher("/signup"), // 회원가입 페이지 접근 허용
                                new AntPathRequestMatcher("/user") // 사용자 정보 접근 허용
                        ).permitAll() // 위 URL에 대한 인증 없이 접근 허용
                        .anyRequest().authenticated() // 위의 URL을 제외한 모든 요청은 인증 필요
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // 커스텀 로그인 페이지 설정
                        .defaultSuccessUrl("/articles") // 로그인 성공 후 이동할 페이지 설정
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 페이지 설정
                        .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                )
                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 기능 비활성화
                .build();

    }

    /**
     * AuthenticationManager 설정 메서드.
     * 사용자 인증을 담당하는 AuthenticationManager를 생성함.
     *
     * @param http HttpSecurity 객체
     * @param bCryptPasswordEncoder 비밀번호 암호화 객체
     * @param userDetailService 사용자 정보 서비스
     * @return AuthenticationManager 객체
     * @throws Exception 설정 과정에서 발생할 수 있는 예외
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // 사용자 정보 서비스 설정
        authProvider.setPasswordEncoder(bCryptPasswordEncoder); // 비밀번호 암호화 설정
        return new ProviderManager(authProvider); // 인증 공급자를 기반으로 AuthenticationManager 생성
    }

    /**
     * 비밀번호를 암호화하는 BCryptPasswordEncoder 빈 등록.
     *
     * @return BCryptPasswordEncoder 객체
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
