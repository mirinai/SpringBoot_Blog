package me.shinsunyoung.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;

import java.time.LocalDateTime;

/**
 * 단일 게시글 정보를 담는 DTO 클래스
 * 게시글을 조회할 때 사용됨
 */
@NoArgsConstructor // 기본 생성자를 자동 생성 (매개변수 없는 생성자)
@Getter // 모든 필드에 대한 Getter 메서드를 자동 생성
public class ArticleViewResponse {

    private Long id; // 게시글 ID
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private LocalDateTime createdAt; // 게시글 생성일

    /**
     * Article 엔티티를 받아 DTO로 변환하는 생성자
     *
     * @param article 게시글 엔티티
     */
    public ArticleViewResponse(Article article) {
        this.id = article.getId(); // 엔티티의 ID 값을 DTO에 저장
        this.title = article.getTitle(); // 엔티티의 제목 값을 DTO에 저장
        this.content = article.getContent(); // 엔티티의 내용 값을 DTO에 저장
        this.createdAt = article.getCreatedAt(); // 엔티티의 생성일 값을 DTO에 저장
    }
}
