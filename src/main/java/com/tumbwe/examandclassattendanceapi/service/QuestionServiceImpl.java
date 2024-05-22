package com.tumbwe.pathventure.service;

import com.tumbwe.pathventure.model.Question;
import com.tumbwe.pathventure.repository.AnswerRepository;
import com.tumbwe.pathventure.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public Question createQuestion(Question question) {
        if (question.getQuestion() == null) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        return questionRepository.save(question);
    }
}
