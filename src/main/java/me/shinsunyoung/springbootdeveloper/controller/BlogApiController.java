package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 블로그 API 컨트롤러
 * 클라이언트의 요청을 받아 게시글을 저장하는 역할 수행
 */
@RequiredArgsConstructor // final 필드를 포함한 생성자를 자동 생성 (의존성 주입을 위해 사용)
@RestController // REST API 컨트롤러로 지정
public class BlogApiController {

    private final BlogService blogService; // 블로그 서비스 (비즈니스 로직 처리)

    /**
     * 게시글 추가 요청을 처리하는 API 엔드포인트
     *
     * @param request 클라이언트가 전송한 게시글 데이터 (JSON 요청 본문)
     * @return 저장된 Article 객체를 포함한 ResponseEntity (HTTP 201 Created 상태 반환)
     */
    @PostMapping("/api/articles") // HTTP POST 요청을 처리하는 엔드포인트
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        // 서비스 계층을 호출하여 게시글 저장
        Article savedArticle = blogService.save(request);

        // HTTP 상태 코드 201 (Created)와 함께 저장된 게시글 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
}
