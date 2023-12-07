package com.example.studentManagementSystem.service.impl;

import com.example.studentManagementSystem.model.Student;
import com.example.studentManagementSystem.model.dto.request.StudentRequestDto;
import com.example.studentManagementSystem.model.dto.response.StudentCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.StudentResponseDto;
import com.example.studentManagementSystem.repository.StudentRepository;
import com.example.studentManagementSystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new student based on the provided StudentRequestDto.
     * Saves the student via the repository and returns the saved student's details as a response DTO.
     */
    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequest) {
        Student student = modelMapper.map(studentRequest, Student.class);
        student.generateId();
        Student savedStudent = studentRepository.createStudent(student);
        return modelMapper.map(savedStudent, StudentResponseDto.class);
    }

    /**
     * Retrieves all students from the repository and maps them to StudentResponseDto.
     * Returns a list of mapped StudentResponseDto objects.
     */
    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.getAllStudents();
        return students.stream().map(s -> modelMapper.map(s, StudentResponseDto.class)).toList();
    }

    /**
     * Retrieves a student by ID from the repository and maps it to StudentResponseDto.
     * Returns the mapped StudentResponseDto.
     */
    @Override
    public StudentResponseDto getStudentById(String studentId) {
        Student foundedStudent = findStudentById(studentId);
        return modelMapper.map(foundedStudent, StudentResponseDto.class);
    }

    /**
     * Retrieves a student's courses by ID from the repository and maps it to StudentCourseResponseDto.
     * Returns the mapped StudentCourseResponseDto.
     */
    @Override
    public StudentCourseResponseDto getStudentCourses(String studentId) {
        Student foundedStudent = studentRepository.getStudentCourses(studentId);
        return modelMapper.map(foundedStudent, StudentCourseResponseDto.class);
    }

    /**
     * Updates a student's details based on the provided StudentRequestDto.
     * Retrieves the student by ID, updates its details, and saves the changes.
     * Returns the updated student's details as a response DTO.
     */
    @Override
    public StudentResponseDto updateStudent(String studentId, StudentRequestDto studentRequest) {
        Student foundedStudent = findStudentById(studentId);
        foundedStudent.setFirstName(studentRequest.getFirstName());
        foundedStudent.setLastName(studentRequest.getLastName());
        foundedStudent.setStudentAge(studentRequest.getStudentAge());
        foundedStudent.setStudentEmail(studentRequest.getStudentEmail());

        Student updatedStudent = studentRepository.updateStudent(studentId, foundedStudent);
        return modelMapper.map(updatedStudent, StudentResponseDto.class);
    }

    /**
     * Deletes a student by ID
     */
    @Override
    public void deleteStudent(String studentId) {
        studentRepository.deleteStudent(studentId);
    }

    /**
     * Find a student by ID Helper Method
     * Returns the found Student entity.
     */
    private Student findStudentById(String studentId) {
        return studentRepository.getStudentById(studentId);
    }
}