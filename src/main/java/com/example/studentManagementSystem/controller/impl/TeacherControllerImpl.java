package com.example.studentManagementSystem.controller.impl;

import com.example.studentManagementSystem.controller.TeacherController;
import com.example.studentManagementSystem.model.dto.request.TeacherRequestDto;
import com.example.studentManagementSystem.model.dto.response.TeacherCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.TeacherResponseDto;
import com.example.studentManagementSystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherControllerImpl implements TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<TeacherResponseDto> createTeacher(@RequestBody TeacherRequestDto teacherRequest) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable("teacherId") String teacherId) {
        return new ResponseEntity<>(teacherService.getTeacherById(teacherId), HttpStatus.OK);
    }

    @GetMapping("/{teacherId}/courses")
    public ResponseEntity<TeacherCourseResponseDto> getTeacherCourses(@PathVariable("teacherId") String teacherId) {
        return new ResponseEntity<>(teacherService.getTeacherCourses(teacherId), HttpStatus.OK);
    }

    @PutMapping("/{teacherId}/update")
    public ResponseEntity<TeacherResponseDto> updateTeacher(@PathVariable("teacherId") String teacherId, @RequestBody TeacherRequestDto teacherRequest) {
        return new ResponseEntity<>(teacherService.updateTeacher(teacherId, teacherRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{teacherId}/delete")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("teacherId") String teacherId) {
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
