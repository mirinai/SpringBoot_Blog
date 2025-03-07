package me.shinsunyoung.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 회원 가입 요청을 처리하기 위한 DTO (Data Transfer Object) 클래스.
 * 클라이언트에서 전달받은 회원 정보(이메일, 비밀번호)를 담는 역할을 함.
 */
@Getter // Lombok을 사용하여 필드의 Getter 메서드 자동 생성
@Setter // Lombok을 사용하여 필드의 Setter 메서드 자동 생성
public class AddUserRequest {

    private String email; // 사용자 이메일

    private String password; // 사용자 비밀번호 (암호화하여 저장해야 함)
}
