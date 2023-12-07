package com.example.studentManagementSystem.controller;

import com.example.studentManagementSystem.model.dto.request.CourseRequestDto;
import com.example.studentManagementSystem.model.dto.response.CourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseStudentResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseTeacherResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CourseController {
    ResponseEntity<CourseTeacherResponseDto> createCourse(@RequestBody CourseRequestDto courseRequest);

    ResponseEntity<List<CourseResponseDto>> getAllCourses();

    ResponseEntity<CourseTeacherResponseDto> getCourseById(@PathVariable("courseId") String courseId);

    ResponseEntity<CourseTeacherResponseDto> updateCourse(@PathVariable("courseId") String courseId, @RequestBody CourseRequestDto courseRequest);

    ResponseEntity<Void> deleteCourse(@PathVariable("courseId") String courseId);

    ResponseEntity<CourseStudentResponseDto> assignStudentsToCourse(@PathVariable("courseId") String courseId, @RequestBody List<String> studentsIds);
}
