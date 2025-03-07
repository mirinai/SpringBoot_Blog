package me.shinsunyoung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.dto.AddUserRequest;
import me.shinsunyoung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스.
 * 회원 가입 기능을 제공하며, 비밀번호를 암호화하여 저장함.
 */
@RequiredArgsConstructor // Lombok을 사용하여 final 필드에 대한 생성자 자동 생성
@Service // Spring의 Service 계층으로 등록
public class UserService {

    private final UserRepository userRepository; // 사용자 정보를 저장 및 조회하는 리포지토리

    private final BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호 암호화를 위한 인코더

    /**
     * 사용자를 데이터베이스에 저장하는 메서드 (회원 가입)
     *
     * @param dto 회원 가입 요청 데이터 (AddUserRequest DTO)
     * @return 생성된 사용자 ID
     */
    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail()) // 이메일 설정
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) // 비밀번호를 암호화하여 저장
                .build()).getId(); // 저장된 사용자 ID 반환
    }
}
