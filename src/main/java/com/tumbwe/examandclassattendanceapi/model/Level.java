package com.tumbwe.pathventure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int title;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}
