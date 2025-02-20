package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dto.ArticleListViewResponse;
import me.shinsunyoung.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles") // "/articles" 경로로 GET 요청이 들어오면 실행
    public String getArticles(Model model) {
        // blogService의 findAll() 메서드를 호출하여 모든 Article 엔티티를 가져온 후
        // 각 Article 객체를 ArticleListViewResponse DTO로 변환하여 리스트로 저장
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new) // Article 엔티티를 DTO로 변환
                .toList(); // 변환된 리스트를 저장

        // 변환된 게시글 리스트를 모델에 추가하여 뷰에서 사용할 수 있도록 설정
        model.addAttribute("articles", articles);

        return "articleList"; // "articleList"라는 이름의 Thymeleaf 템플릿을 반환
    }
}
