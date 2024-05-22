package com.tumbwe.pathventure.service;

import com.tumbwe.pathventure.model.Quiz;
import com.tumbwe.pathventure.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public Quiz createQuiz(Quiz quiz) {
         if(Objects.equals(quiz.getTitle(), "")){
             throw new IllegalArgumentException("Quiz title cannot be empty");
         }
        return quizRepository.save(quiz);
    }
}
