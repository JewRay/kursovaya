package com.example.kurs.service;

import com.example.kurs.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
