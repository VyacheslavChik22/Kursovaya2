package com.example.kursovaya2.service.impl;

import com.example.kursovaya2.data.Question;
import com.example.kursovaya2.exception.QuestionAllReadyExistException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.service.QuestionService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionServiceTest {

    QuestionService questionService;

   private static  final Question QUESTION = new Question("Question1", "Answer1") ;
   private static  final Question QUESTION2 = new Question("Question2", "Answer2") ;

   @BeforeEach
   void setUpp(){
       questionService = new QuestionServiceImpl();
   }

   @Test
    void shouldAddQuestions() {
       Question result = questionService.add(QUESTION.getQuestion(), QUESTION.getAnswer());
       assertThat(result).isEqualTo(QUESTION);
       assertThat(questionService.getAll().size()).isEqualTo(1);
   }

    @Test
    void shouldRemoveQuestions() {
        questionService.add(QUESTION.getQuestion(), QUESTION.getAnswer());
        Question result = questionService.remove(QUESTION);
        assertThat(result).isEqualTo(QUESTION);
        assertThat(questionService.getAll().size()).isEqualTo(0);
    }

    @Test
    void shouldThrowExceptionRemoveQuestions() {

        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(()->questionService.remove(QUESTION));

    }

    @Test
    void shouldQuestionAllReadyExistExceptionQuestions() {
        questionService.add(QUESTION.getQuestion(), QUESTION.getAnswer());
        assertThatExceptionOfType(QuestionAllReadyExistException.class).isThrownBy(()-> questionService.add(QUESTION.getQuestion(), QUESTION.getAnswer()));

    }


    @Test
    void shouldReturnRandomQuestions() {
        questionService.add(QUESTION.getQuestion(), QUESTION.getAnswer());
        questionService.add(QUESTION2.getQuestion(), QUESTION2.getAnswer());
        Question result = questionService.getRandomQuestion();
        assertThat(result).isIn(QUESTION,QUESTION2);

    }
}


