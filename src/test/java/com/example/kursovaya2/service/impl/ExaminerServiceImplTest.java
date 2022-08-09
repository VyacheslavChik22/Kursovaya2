package com.example.kursovaya2.service.impl;

import com.example.kursovaya2.data.Question;
import com.example.kursovaya2.exception.ExcessNumberOfQuestionsException;
import com.example.kursovaya2.service.QuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void qetQuestionsNegativeTest() {
        Assertions.assertThatExceptionOfType(ExcessNumberOfQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
        Assertions.assertThatExceptionOfType(ExcessNumberOfQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    public void qetQuestionsPositiveTest() {
        List<Question> questions = new ArrayList<>();                         // создадим лист вопросов
        questions.add(new Question("Question1", "Answer1"));
        questions.add(new Question("Question2", "Answer2"));
        questions.add(new Question("Question3", "Answer3"));
        questions.add(new Question("Question4", "Answer4"));
        questions.add(new Question("Question5", "Answer5"));


        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(questions.get(0), questions.get(1), questions.get(0), questions.get(2));
        assertThat(examinerService.getQuestions(3)).containsExactlyInAnyOrder(questions.get(0), questions.get(1), questions.get(2));

        when(questionService.getRandomQuestion()).thenReturn(questions.get(0), questions.get(1), questions.get(0), questions.get(2), questions.get(3), questions.get(1));
        assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(questions.get(0), questions.get(1), questions.get(2), questions.get(3));
    }
}

