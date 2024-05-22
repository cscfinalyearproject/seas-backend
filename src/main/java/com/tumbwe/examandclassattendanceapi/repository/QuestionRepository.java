package com.tumbwe.pathventure.repository;

import com.tumbwe.pathventure.model.Level;
import com.tumbwe.pathventure.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findAllByLevel(Level level);
}
