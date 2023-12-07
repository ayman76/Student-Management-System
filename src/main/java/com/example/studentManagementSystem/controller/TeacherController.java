package com.example.studentManagementSystem.controller;

import com.example.studentManagementSystem.model.dto.request.TeacherRequestDto;
import com.example.studentManagementSystem.model.dto.response.TeacherCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.TeacherResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeacherController {

    ResponseEntity<TeacherResponseDto> createTeacher(@RequestBody TeacherRequestDto teacherRequest);

    ResponseEntity<List<TeacherResponseDto>> getAllTeachers();

    ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable("teacherId") String teacherId);

    ResponseEntity<TeacherCourseResponseDto> getTeacherCourses(@PathVariable("teacherId") String teacherId);

    ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable("teacherId") String teacherId, @RequestBody TeacherRequestDto teacherRequest);

    ResponseEntity<Void> deleteTeacher(@PathVariable("teacherId") String teacherId);
}

