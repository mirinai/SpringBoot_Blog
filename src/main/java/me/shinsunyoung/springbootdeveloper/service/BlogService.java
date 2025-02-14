package me.shinsunyoung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 블로그 서비스 클래스
 * 게시글과 관련된 비즈니스 로직을 처리
 */
@RequiredArgsConstructor // final 필드가 있는 생성자를 자동으로 생성 (의존성 주입을 위해 사용)
@Service // 스프링의 서비스 컴포넌트로 등록
public class BlogService {

    private final BlogRepository blogRepository; // 블로그 저장소 (JPA Repository)

    /**
     * 게시글을 저장하는 메서드
     *
     * @param request AddArticleRequest DTO (제목과 내용 포함)
     * @return 저장된 Article 엔티티 객체
     */
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity()); // DTO를 엔티티로 변환 후 저장
    }

    /**
     * 모든 게시글을 조회하는 메서드
     *
     * @return 데이터베이스에 저장된 모든 Article 엔티티 목록 (List 형태)
     */
    public List<Article> findAll() {
        return blogRepository.findAll(); // JPA의 기본 제공 메서드를 사용하여 모든 게시글 조회
    }

}
