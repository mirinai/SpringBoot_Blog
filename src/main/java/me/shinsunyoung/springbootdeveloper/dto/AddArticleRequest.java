package me.shinsunyoung.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;

/**
 * 게시글 추가 요청을 처리하는 DTO (Data Transfer Object)
 */
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 포함하는 생성자 자동 생성
@Getter // Getter 메서드 자동 생성
public class AddArticleRequest {

    private String title; // 게시글 제목

    private String content; // 게시글 내용

    /**
     * DTO를 Article 엔티티로 변환하는 메서드
     *
     * @return Article 엔티티 객체
     */
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}