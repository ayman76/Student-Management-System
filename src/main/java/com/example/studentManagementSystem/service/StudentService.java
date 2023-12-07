package com.example.studentManagementSystem.service;

import com.example.studentManagementSystem.model.dto.request.StudentRequestDto;
import com.example.studentManagementSystem.model.dto.response.StudentCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentRequest);

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(String studentId);

    StudentCourseResponseDto getStudentCourses(String studentId);

    StudentResponseDto updateStudent(String studentId, StudentRequestDto studentRequest);

    void deleteStudent(String studentId);
}
