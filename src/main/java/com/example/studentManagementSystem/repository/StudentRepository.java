package com.example.studentManagementSystem.repository;

import com.example.studentManagementSystem.model.Student;

import java.util.List;

public interface StudentRepository {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(String studentId);

    Student getStudentCourses(String studentId);

    Student updateStudent(String studentId, Student student);

    void deleteStudent(String studentId);
}
