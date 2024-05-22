package com.tumbwe.pathventure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updated_at;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Level> levels;
}
