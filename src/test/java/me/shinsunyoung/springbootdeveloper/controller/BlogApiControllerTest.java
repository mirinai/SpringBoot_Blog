package me.shinsunyoung.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.shinsunyoung.springbootdeveloper.domain.Article;
import me.shinsunyoung.springbootdeveloper.dto.AddArticleRequest;
import me.shinsunyoung.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * BlogApiController 테스트 클래스
 * API의 동작을 검증하는 통합 테스트 수행
 */
@SpringBootTest // 스프링 부트 애플리케이션 전체를 로드하여 테스트 실행
@AutoConfigureMockMvc // MockMvc를 자동 설정하여 테스트 환경에서 컨트롤러를 호출할 수 있도록 함
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc; // MockMvc를 이용해 API 요청을 테스트

    @Autowired
    protected ObjectMapper objectMapper; // JSON 데이터를 직렬화/역직렬화하기 위한 객체

    @Autowired
    private WebApplicationContext context; // 웹 애플리케이션 컨텍스트 (테스트 환경에서 사용)

    @Autowired
    BlogRepository blogRepository; // 테스트에서 사용할 BlogRepository

    /**
     * 각 테스트 실행 전에 실행되는 메서드
     * - MockMvc 설정 초기화
     * - 테스트 실행 전 BlogRepository의 데이터를 모두 삭제하여 테스트 간 영향을 방지
     */
    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context) // 테스트용 MockMvc 설정
                .build();

        blogRepository.deleteAll(); // 테스트 시작 전에 기존 데이터 삭제
    }

    @DisplayName("addArticle: 블로그에 글 넣기") // 테스트 이름을 명확하게 지정
    @Test
    public void addArticle() throws Exception {

        // given (테스트 데이터 준비)
        final String url = "/api/articles"; // 테스트할 API 엔드포인트 URL
        final String title = "title"; // 테스트할 게시글 제목
        final String content = "content"; // 테스트할 게시글 내용
        final AddArticleRequest userRequest = new AddArticleRequest(title, content); // 요청 DTO 생성

        // 객체를 JSON 문자열로 변환 (직렬화)
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when (API 요청 실행)
        // MockMvc를 사용하여 POST 요청을 보냄
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE) // 요청 타입을 JSON으로 지정
                .content(requestBody) // JSON 데이터를 요청 본문에 포함
        );

        // then (결과 검증)
        // HTTP 응답 상태 코드가 201 Created인지 확인
        result.andExpect(status().isCreated());

        // 데이터베이스에서 저장된 게시글 목록 조회
        List<Article> articles = blogRepository.findAll();

        // 저장된 게시글 개수가 1개인지 확인
        assertThat(articles.size()).isEqualTo(1);

        // 저장된 게시글의 제목이 요청한 제목과 같은지 확인
        assertThat(articles.get(0).getTitle()).isEqualTo(title);

        // 저장된 게시글의 내용이 요청한 내용과 같은지 확인
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }
}
