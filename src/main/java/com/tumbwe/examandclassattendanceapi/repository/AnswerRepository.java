package com.tumbwe.pathventure.repository;

import com.tumbwe.pathventure.model.Answer;
import com.tumbwe.pathventure.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findAllByQuestion(Question question);
}
