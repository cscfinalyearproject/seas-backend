package com.tumbwe.pathventure.repository;

import com.tumbwe.pathventure.model.Level;
import com.tumbwe.pathventure.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> findByLevelsEquals(List<Level> levels);
}
