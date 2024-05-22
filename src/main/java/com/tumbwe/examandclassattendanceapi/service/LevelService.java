package com.tumbwe.pathventure.service;

import com.tumbwe.pathventure.model.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface LevelService {
    Level createLevel(Level level);
}
