package com.example.studentManagementSystem.controller.impl;

import com.example.studentManagementSystem.controller.CourseController;
import com.example.studentManagementSystem.model.dto.request.CourseRequestDto;
import com.example.studentManagementSystem.model.dto.response.CourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseStudentResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseTeacherResponseDto;
import com.example.studentManagementSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseTeacherResponseDto> createCourse(@RequestBody CourseRequestDto courseRequest) {
        return new ResponseEntity<>(courseService.createCourse(courseRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseTeacherResponseDto> getCourseById(@PathVariable("courseId") String courseId) {
        return new ResponseEntity<>(courseService.getCourseById(courseId), HttpStatus.OK);
    }

    @PutMapping("/{courseId}/update")
    public ResponseEntity<CourseTeacherResponseDto> updateCourse(@PathVariable("courseId") String courseId, @RequestBody CourseRequestDto courseRequest) {
        return new ResponseEntity<>(courseService.updateCourse(courseId, courseRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/delete")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") String courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{courseId}/assignStudent")
    public ResponseEntity<CourseStudentResponseDto> assignStudentsToCourse(@PathVariable("courseId") String courseId, @RequestBody List<String> studentsIds) {
        return new ResponseEntity<>(courseService.assignStudentsToCourse(courseId, studentsIds), HttpStatus.CREATED);
    }
}
