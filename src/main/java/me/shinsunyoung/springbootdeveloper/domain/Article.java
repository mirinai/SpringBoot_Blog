package me.shinsunyoung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Article 엔티티 클래스
 * 데이터베이스의 Article 테이블과 매핑됨
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 보호 수준으로 설정하여 객체 생성 제한
public class Article {

    @Id // 기본 키(Primary Key) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성 전략 설정 (IDENTITY: DB가 자동 생성)
    @Column(name = "id", updatable = false) // 컬럼명 지정 및 수정 불가능하도록 설정
    private Long id;

    @Column(name = "title", nullable = false) // 컬럼명 지정 및 NULL 허용 안 함
    private String title;

    @Column(name = "content", nullable = false) // 컬럼명 지정 및 NULL 허용 안 함
    private String content;

    /**
     * Article 객체를 생성하는 생성자
     *
     * @param title   기사 제목
     * @param content 기사 내용
     */
    @Builder // 빌더 패턴을 사용하여 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
