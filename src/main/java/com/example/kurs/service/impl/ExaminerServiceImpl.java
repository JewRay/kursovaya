package com.example.kurs.service.impl;

import com.example.kurs.exceptions.NotEnougnQuestionException;
import com.example.kurs.model.Question;
import com.example.kurs.service.ExaminerService;
import com.example.kurs.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    public final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new NotEnougnQuestionException();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
