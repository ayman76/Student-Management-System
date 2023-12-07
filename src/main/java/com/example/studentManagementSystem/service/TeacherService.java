package com.example.studentManagementSystem.service;

import com.example.studentManagementSystem.model.dto.request.TeacherRequestDto;
import com.example.studentManagementSystem.model.dto.response.TeacherCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.TeacherResponseDto;

import java.util.List;

public interface TeacherService {

    TeacherResponseDto createTeacher(TeacherRequestDto teacherRequest);

    List<TeacherResponseDto> getAllTeachers();

    TeacherResponseDto getTeacherById(String teacherId);

    TeacherCourseResponseDto getTeacherCourses(String teacherId);

    TeacherResponseDto updateTeacher(String teacherId, TeacherRequestDto teacherRequest);

    void deleteTeacher(String teacherId);
}
