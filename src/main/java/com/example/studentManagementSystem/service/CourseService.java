package com.example.studentManagementSystem.service;

import com.example.studentManagementSystem.model.dto.request.CourseRequestDto;
import com.example.studentManagementSystem.model.dto.response.CourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseStudentResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseTeacherResponseDto;

import java.util.List;

public interface CourseService {

    CourseTeacherResponseDto createCourse(CourseRequestDto courseRequest);

    List<CourseResponseDto> getAllCourses();

    CourseTeacherResponseDto getCourseById(String courseId);

    CourseTeacherResponseDto updateCourse(String courseId, CourseRequestDto courseRequest);

    CourseStudentResponseDto assignStudentsToCourse(String courseId, List<String> students);

    void deleteCourse(String courseId);
}
