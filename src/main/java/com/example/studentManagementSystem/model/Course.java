package com.example.studentManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "course")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @Column(name = "id")
    private String courseId;

    private String courseName;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();

    /**
     * It generates a unique identifier for the 'courseId' field by combining the prefix
     * 'COURSE_' with a substring of a randomly generated UUID, resulting in a unique
     * identifier of length 11 characters.
     */
    @PrePersist
    public void generateId() {
        this.courseId = "COURSE_" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6).toUpperCase();
    }
}
