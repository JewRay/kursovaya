package com.example.kurs;

import com.example.kurs.exceptions.NotEnougnQuestionException;
import com.example.kurs.model.Question;
import com.example.kurs.service.QuestionService;
import com.example.kurs.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> questions = List.of(
            new Question("Question 1", "Answer 1"),
            new Question("Question 2", "Answer 2"),
            new Question("Question 3", "Answer 3"),
            new Question("Question 4", "Answer 4"),
            new Question("Question 5", "Answer 5")

    );

    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    public void getQuestionNegativeTest() {
        assertThatExceptionOfType(NotEnougnQuestionException.class)
                .isThrownBy(() -> examinerService.getQuestion(questions.size() + 1));
    }

    @Test
    public void getQuestionPositiveTest() {
        when(questionService.getRandomQuestion())
                .thenReturn(
                        new Question("Question 5", "Answer 5"),
                        new Question("Question 2", "Answer 2"),
                        new Question("Question 1", "Answer 1"),
                        new Question("Question 1", "Answer 1"),
                        new Question("Question 1", "Answer 1"),
                        new Question("Question 3", "Answer 3"),
                        new Question("Question 4", "Answer 4")
                );
        assertThat(examinerService.getQuestion(5))
                .containsExactlyInAnyOrder(
                        new Question("Question 1", "Answer 1"),
                        new Question("Question 2", "Answer 2"),
                        new Question("Question 3", "Answer 3"),
                        new Question("Question 4", "Answer 4"),
                        new Question("Question 5", "Answer 5")

                );

    }
}
