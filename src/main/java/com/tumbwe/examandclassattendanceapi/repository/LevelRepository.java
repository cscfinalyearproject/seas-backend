package com.tumbwe.pathventure.repository;

import com.tumbwe.pathventure.model.Level;
import com.tumbwe.pathventure.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LevelRepository extends JpaRepository<Level, UUID> {
    Level findLevelByQuiz(Quiz quiz);
}
