package com.example.studentManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "quiz")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @Column(name = "id")
    private String quizId;

    @ManyToOne
    private Course course;

    private String title;

    /**
     * It generates a unique identifier for the 'quizId' field by combining the prefix
     * 'QUIZ_' with a substring of a randomly generated UUID, resulting in a unique
     * identifier of length 11 characters.
     */
    @PrePersist
    public void generateId() {
        this.quizId = "QUIZ_" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6).toUpperCase();
    }
}
