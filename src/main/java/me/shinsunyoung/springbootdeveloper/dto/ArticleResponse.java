package me.shinsunyoung.springbootdeveloper.dto;

import lombok.Getter;
import me.shinsunyoung.springbootdeveloper.domain.Article;

/**
 * 게시글 응답 DTO (Data Transfer Object)
 * 클라이언트에게 게시글 정보를 반환할 때 사용
 */
@Getter // 필드의 Getter 메서드를 자동 생성
public class ArticleResponse {

    private final String title; // 게시글 제목
    private final String content; // 게시글 내용

    /**
     * Article 엔티티를 기반으로 응답 DTO를 생성하는 생성자
     *
     * Article을 직접 받아서 dto로 바꿈
     *
     * @param article 게시글 엔티티
     */
    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
