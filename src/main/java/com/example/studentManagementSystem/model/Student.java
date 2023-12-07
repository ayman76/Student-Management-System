package com.example.studentManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "student")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "id")
    private String studentId;

    private String firstName;

    private String lastName;

    @Column(name = "email")
    private String studentEmail;

    private int studentAge;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    /**
     * It generates a unique identifier for the 'studentId' field by combining the prefix
     * 'STUD_' with a substring of a randomly generated UUID, resulting in a unique
     * identifier of length 11 characters.
     */
    @PrePersist
    public void generateId() {
        this.studentId = "STUD_" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6).toUpperCase();
    }

}