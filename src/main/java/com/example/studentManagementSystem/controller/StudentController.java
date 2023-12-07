package com.example.studentManagementSystem.controller;

import com.example.studentManagementSystem.model.dto.request.StudentRequestDto;
import com.example.studentManagementSystem.model.dto.response.StudentCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.StudentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentController {
    ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto studentRequest);

    ResponseEntity<List<StudentResponseDto>> getAllStudents();

    ResponseEntity<StudentResponseDto> getStudentById(@PathVariable("studentId") String studentId);

    ResponseEntity<StudentCourseResponseDto> getStudentCourses(@PathVariable("studentId") String studentId);

    ResponseEntity<StudentResponseDto> updateStudent(@PathVariable("studentId") String studentId, @RequestBody StudentRequestDto studentRequest);

    ResponseEntity<Void> deleteStudent(@PathVariable("studentId") String studentId);
}
