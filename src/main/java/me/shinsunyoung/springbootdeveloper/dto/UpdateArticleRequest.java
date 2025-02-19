package me.shinsunyoung.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 수정 요청을 처리하는 DTO (Data Transfer Object)
 * 클라이언트가 게시글 수정 요청을 보낼 때 사용하는 데이터 객체
 */
@NoArgsConstructor // 기본 생성자 자동 생성 (JSON 역직렬화 시 필요)
@AllArgsConstructor // 모든 필드를 포함하는 생성자 자동 생성
@Getter // Getter 메서드 자동 생성
public class UpdateArticleRequest {

    private String title; // 수정할 게시글 제목
    private String content; // 수정할 게시글 내용
}
