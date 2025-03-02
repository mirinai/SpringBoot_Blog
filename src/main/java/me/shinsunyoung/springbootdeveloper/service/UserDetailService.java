package me.shinsunyoung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 사용자 인증을 담당하는 서비스 클래스.
 * Spring Security의 UserDetailsService를 구현하여 사용자 정보를 로드함.
 */
@RequiredArgsConstructor // Lombok을 사용하여 생성자를 자동 생성 (final 필드 주입)
@Service // Spring의 Service 계층으로 등록
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository; // User 엔티티의 DB 접근을 위한 Repository

    /**
     * 주어진 이메일을 사용하여 사용자 정보를 조회하는 메서드.
     * Spring Security의 인증 과정에서 호출됨.
     *
     * @param email 사용자 이메일
     * @return User 객체 (UserDetails를 구현한 엔티티)
     * @throws IllegalArgumentException 이메일에 해당하는 사용자가 없을 경우 예외 발생
     */
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
