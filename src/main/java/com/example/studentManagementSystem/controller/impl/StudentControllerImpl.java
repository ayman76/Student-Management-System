package com.example.studentManagementSystem.controller.impl;

import com.example.studentManagementSystem.controller.StudentController;
import com.example.studentManagementSystem.model.dto.request.StudentRequestDto;
import com.example.studentManagementSystem.model.dto.response.StudentCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.StudentResponseDto;
import com.example.studentManagementSystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto studentRequest) {
        return new ResponseEntity<>(studentService.createStudent(studentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable("studentId") String studentId) {
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<StudentCourseResponseDto> getStudentCourses(@PathVariable("studentId") String studentId) {
        return new ResponseEntity<>(studentService.getStudentCourses(studentId), HttpStatus.OK);
    }

    @PutMapping("/{studentId}/update")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable("studentId") String studentId, @RequestBody StudentRequestDto studentRequest) {
        return new ResponseEntity<>(studentService.updateStudent(studentId, studentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") String studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
