package me.shinsunyoung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.dto.UpdateArticleRequest;
import me.shinsunyoung.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 특정 ID를 가진 게시글을 조회하는 메서드
     *
     * @param id 조회할 게시글의 ID
     * @return ID에 해당하는 Article 엔티티 객체
     * @throws IllegalArgumentException 해당 ID의 게시글이 존재하지 않을 경우 예외 발생
     */
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id)); // 존재하지 않으면 예외 발생
    }


    /**
     * 특정 ID를 가진 게시글을 삭제하는 메서드
     *
     * @param id 삭제할 게시글의 ID
     */
    public void delete(long id) {
        blogRepository.deleteById(id); // JPA에서 제공하는 기본 메서드를 사용하여 ID 기준으로 삭제
    }

    /**
     * 특정 ID를 가진 게시글을 수정하는 메서드
     *
     * @param id 수정할 게시글의 ID
     * @param request 게시글 수정 요청 객체 (수정할 제목과 내용 포함)
     * @return 수정된 Article 엔티티 객체
     * @throws IllegalArgumentException 게시글이 존재하지 않을 경우 예외 발생
     */
    @Transactional // 해당 메서드를 하나의 트랜잭션으로 처리 (변경 감지 기능 활성화)
    public Article update(long id, UpdateArticleRequest request) {

        // ID를 기준으로 게시글을 조회, 존재하지 않으면 예외 발생
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        // 조회된 엔티티의 제목과 내용을 변경 (JPA 변경 감지 적용)
        article.update(request.getTitle(), request.getContent());

        // 변경 감지(Dirty Checking)에 의해 트랜잭션 종료 시 자동으로 DB에 반영됨
        return article;
    }

}
