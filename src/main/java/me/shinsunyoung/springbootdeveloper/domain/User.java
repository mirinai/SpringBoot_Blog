package me.shinsunyoung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 사용자 정보를 저장하는 엔티티 클래스.
 * Spring Security의 UserDetails를 구현하여 인증과 권한 관리를 지원함.
 */
@Table(name = "users") // 데이터베이스의 users 테이블과 매핑
@NoArgsConstructor // 기본 생성자 자동 생성 (Lombok)
@Getter // 클래스의 필드에 대한 Getter 메서드 자동 생성 (Lombok)
@Entity // JPA 엔티티로 선언
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키를 자동 증가 전략으로 설정
    @Column(name = "id", updatable = false) // id 컬럼, 수정 불가능
    private Long id;

    @Column(name = "email", nullable = false, updatable = true) // email 컬럼, 반드시 값이 있어야 함
    private String email;

    @Column(name = "password") // password 컬럼
    private String password;

    /**
     * 생성자 (Builder 패턴 사용)
     *
     * @param email    사용자 이메일
     * @param password 사용자 비밀번호
     */
    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    /**
     * 사용자의 권한을 반환하는 메서드 (Spring Security)
     *
     * @return GrantedAuthority 리스트
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("users"));
    }

    /**
     * 사용자명을 반환하는 메서드 (Spring Security)
     *
     * @return 사용자 이메일 (username 역할)
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * 사용자의 비밀번호를 반환하는 메서드 (Spring Security)
     *
     * @return 사용자 비밀번호
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 계정이 만료되지 않았는지 여부를 반환
     *
     * @return true (항상 활성화된 상태)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겨있지 않은지 여부를 반환
     *
     * @return true (항상 잠금 해제된 상태)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 사용자의 자격 증명이 만료되지 않았는지 여부를 반환
     *
     * @return true (항상 유효한 상태)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 활성화되어 있는지 여부를 반환
     *
     * @return true (항상 활성화된 상태)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
