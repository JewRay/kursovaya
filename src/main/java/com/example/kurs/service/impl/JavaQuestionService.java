package com.example.kurs.service.impl;

import com.example.kurs.exceptions.QuestionAlreadyExistsException;
import com.example.kurs.exceptions.QuestionAreEmptyException;
import com.example.kurs.exceptions.QuestionNotFoundException;
import com.example.kurs.model.Question;
import com.example.kurs.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistsException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new QuestionAreEmptyException();
        }
        int counter = 0;
        int count = random.nextInt(questions.size());
        for (Question question:questions){
            if(counter==count){
                return question;
            }
            counter++;
        }
        return null;
    }
}
