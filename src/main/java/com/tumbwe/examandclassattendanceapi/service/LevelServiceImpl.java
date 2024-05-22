package com.tumbwe.pathventure.service;

import com.tumbwe.pathventure.model.Level;
import com.tumbwe.pathventure.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Override
    public Level createLevel(Level level) {

        if (level.getTitle()==0) {
            level.setTitle(1);
        }
        return levelRepository.save(level);
    }
}
