package com.example.kursovaya2.service.impl;

import com.example.kursovaya2.data.Question;
import com.example.kursovaya2.exception.QuestionAllReadyExistException;
import com.example.kursovaya2.exception.QuestionNotFoundException;
import com.example.kursovaya2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public QuestionServiceImpl() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAllReadyExistException();
        }
        questions.add(question); // Добавляем вопрос
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();//Если не найден вопрос, выбрасываем исключение
        }
        questions.remove(question); // удаляем вопрос
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions); //Копия коллекции для инкапсуляции
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }

}
