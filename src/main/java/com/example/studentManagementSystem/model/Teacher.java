package com.example.studentManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "teacher")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @Column(name = "id")
    private String teacherId;

    private String firstName;

    private String lastName;

    @Column(name = "email")
    private String teacherEmail;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    /**
     * It generates a unique identifier for the 'teacherId' field by combining the prefix
     * 'TEACHER_' with a substring of a randomly generated UUID, resulting in a unique
     * identifier of length 11 characters.
     */
    @PrePersist
    public void generateId() {
        this.teacherId = "TEACHER_" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6).toUpperCase();
    }
}
