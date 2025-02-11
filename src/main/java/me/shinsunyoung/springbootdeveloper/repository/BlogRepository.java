package me.shinsunyoung.springbootdeveloper.repository;

import me.shinsunyoung.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BlogRepository 인터페이스
 * Article 엔티티를 관리하는 JPA Repository
 */
public interface BlogRepository extends JpaRepository<Article, Long> {
    // JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공함
}
