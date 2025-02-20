package me.shinsunyoung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller // 해당 클래스가 Spring MVC의 컨트롤러 역할을 수행하도록 설정
public class ExampleController {

    @GetMapping("/thymeleaf/example") // "/thymeleaf/example" 경로로 GET 요청이 들어오면 실행
    public String thymeleafExample(Model model) {

        // 예제 데이터를 생성하여 모델에 추가
        Person examplePerson = new Person();
        examplePerson.setId(1L); // ID 설정
        examplePerson.setName("joan"); // 이름 설정
        examplePerson.setAge(24); // 나이 설정
        examplePerson.setHobbies(List.of("exercise", "reading")); // 취미 리스트 설정

        model.addAttribute("person", examplePerson); // "person"이라는 이름으로 모델에 객체 추가
        model.addAttribute("today", LocalDate.now()); // 현재 날짜를 "today"라는 이름으로 모델에 추가

        return "example"; // "example"이라는 이름의 Thymeleaf 템플릿을 반환
    }

    @Setter
    @Getter
    class Person { // 내부 클래스 Person 정의 (개인 정보를 저장하는 데이터 클래스)
        private Long id; // 사람의 ID
        private String name; // 이름
        private int age; // 나이
        private List<String> hobbies; // 취미 리스트
    }
}
