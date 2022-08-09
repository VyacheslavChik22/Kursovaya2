package com.example.kursovaya2.service;

import com.example.kursovaya2.data.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

     Question add(Question question);


    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

}