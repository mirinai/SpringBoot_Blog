package me.shinsunyoung.springbootdeveloper.dto;

import lombok.Getter;
import me.shinsunyoung.springbootdeveloper.domain.Article;

@Getter // 클래스의 필드에 대한 Getter 메서드를 자동 생성
public class ArticleListViewResponse {

    private final Long id; // 게시글 ID
    private final String title; // 게시글 제목
    private final String content; // 게시글 내용

    // Article 엔티티 객체를 받아 DTO로 변환하는 생성자
    public ArticleListViewResponse(Article article) {
        this.id = article.getId(); // 엔티티에서 ID 값을 가져와 설정
        this.title = article.getTitle(); // 엔티티에서 제목 값을 가져와 설정
        this.content = article.getContent(); // 엔티티에서 내용 값을 가져와 설정
    }
}
