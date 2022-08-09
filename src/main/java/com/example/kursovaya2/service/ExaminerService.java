package com.example.kursovaya2.service;

import com.example.kursovaya2.data.Question;

import java.util.Collection;

public interface ExaminerService {

    abstract Collection<Question> getQuestions(int amount);
}

