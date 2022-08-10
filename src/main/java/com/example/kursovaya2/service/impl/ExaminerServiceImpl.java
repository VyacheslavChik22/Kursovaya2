package com.example.kursovaya2.service.impl;

import com.example.kursovaya2.data.Question;
import com.example.kursovaya2.exception.ExcessNumberOfQuestionsException;
import com.example.kursovaya2.service.ExaminerService;
import com.example.kursovaya2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new ExcessNumberOfQuestionsException();
        }
        Set<Question> result = new HashSet<>(amount);
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
