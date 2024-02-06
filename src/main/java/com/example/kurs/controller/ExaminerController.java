package com.example.kurs.controller;

import com.example.kurs.model.Question;
import com.example.kurs.service.ExaminerService;
import com.example.kurs.service.impl.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
public class ExaminerController {
    private final ExaminerService examinerService;

    public ExaminerController(ExaminerServiceImpl service) {
        this.examinerService = service;
    }

    @GetMapping(path = "/exam/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestion(amount);
    }

}
