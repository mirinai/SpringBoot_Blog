package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.dto.ArticleResponse;
import me.shinsunyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyoung.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 블로그 API 컨트롤러
 * 클라이언트의 요청을 받아 게시글을 저장하는 역할 수행
 */
@RequiredArgsConstructor // final 필드를 포함한 생성자를 자동 생성 (의존성 주입을 위해 사용)
@RestController // REST API 컨트롤러로 지정
public class BlogApiController {

    private final BlogService blogService; // 블로그 서비스 (비즈니스 로직 처리)


    /**
     * 모든 게시글을 조회하는 API 엔드포인트
     *
     * @return 저장된 모든 게시글을 ArticleResponse DTO 형태로 반환 (HTTP 200 OK 상태)
     */
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {

        // 서비스 계층에서 모든 게시글을 조회하고, ArticleResponse DTO 리스트로 변환
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new) // Article 엔티티를 ArticleResponse DTO로 변환
                .toList(); // 변환된 결과를 리스트로 저장

        // HTTP 200 OK 상태와 함께 게시글 목록 반환
        return ResponseEntity.ok().body(articles);
    }



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

    /**
     * 특정 ID를 가진 게시글을 조회하는 API 엔드포인트
     *
     * @param id 조회할 게시글의 ID (URL 경로 변수)
     * @return 조회된 게시글을 ArticleResponse DTO 형태로 반환 (HTTP 200 OK 상태)
     */
    @GetMapping("/api/articles/{id}") // 특정 ID의 게시글을 조회하는 GET 요청 엔드포인트
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") long id) {

        // 서비스 계층에서 ID를 기준으로 게시글 조회
        Article article = blogService.findById(id);

        // 조회된 게시글을 ArticleResponse DTO로 변환하여 HTTP 200 OK 응답으로 반환
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    /**
     * 특정 ID를 가진 게시글을 삭제하는 API 엔드포인트
     *
     * @param id 삭제할 게시글의 ID (URL 경로 변수)
     * @return HTTP 200 OK 상태 반환 (본문 없음)
     */
    @DeleteMapping("/api/articles/{id}") // 특정 ID의 게시글을 삭제하는 DELETE 요청 엔드포인트
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) {

        // 서비스 계층을 호출하여 ID에 해당하는 게시글 삭제
        blogService.delete(id);

        // HTTP 200 OK 상태 코드만 반환 (응답 본문 없음)
        return ResponseEntity.ok().build();
    }

    /**
     * 특정 ID를 가진 게시글을 수정하는 API 엔드포인트
     *
     * @param id 수정할 게시글의 ID (URL 경로 변수)
     * @param request 게시글 수정 요청 객체 (수정할 제목과 내용 포함)
     * @return 수정된 게시글을 포함한 HTTP 200 OK 응답
     */
    @PutMapping("/api/articles/{id}") // 특정 ID의 게시글을 수정하는 PUT 요청 엔드포인트
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id,
                                                 @RequestBody UpdateArticleRequest request) {

        // 서비스 계층을 호출하여 게시글 수정
        Article updatedArticle = blogService.update(id, request);

        // HTTP 200 OK 상태와 함께 수정된 게시글 반환
        return ResponseEntity.ok()
                .body(updatedArticle);
    }




}
