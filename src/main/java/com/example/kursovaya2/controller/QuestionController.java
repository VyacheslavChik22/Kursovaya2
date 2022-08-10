package com.example.kursovaya2.controller;

import com.example.kursovaya2.data.Question;
import com.example.kursovaya2.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("java")
public class QuestionController {

    private final QuestionService questionService; // Заинжектим сервис методов
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,@RequestParam String answer){
    return questionService.add(question,answer);
    }

    @GetMapping("/find")
    public Collection<Question> getQuestion(){
        return questionService.getAll();
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,@RequestParam String answer){
        return questionService.remove(new Question(question,answer));
    }

}
