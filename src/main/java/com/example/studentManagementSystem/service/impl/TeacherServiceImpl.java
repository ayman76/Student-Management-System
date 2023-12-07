package com.example.studentManagementSystem.service.impl;

import com.example.studentManagementSystem.model.Teacher;
import com.example.studentManagementSystem.model.dto.request.TeacherRequestDto;
import com.example.studentManagementSystem.model.dto.response.TeacherCourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.TeacherResponseDto;
import com.example.studentManagementSystem.repository.TeacherRepository;
import com.example.studentManagementSystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new teacher based on the provided TeacherRequestDto.
     * Saves the teacher via the repository and returns the saved teacher's details as a response DTO.
     */
    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequest) {
        Teacher teacher = modelMapper.map(teacherRequest, Teacher.class);
        teacher.generateId();
        Teacher savedTeacher = teacherRepository.createTeacher(teacher);
        return modelMapper.map(savedTeacher, TeacherResponseDto.class);
    }

    /**
     * Retrieves all teachers from the repository and maps them to TeacherResponseDto.
     * Returns a list of mapped TeacherResponseDto objects.
     */
    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.getAllTeachers();
        return teachers.stream().map(t -> modelMapper.map(t, TeacherResponseDto.class)).toList();
    }

    /**
     * Retrieves a teacher by ID from the repository along with associated courses.
     * Returns the mapped TeacherResponseDto with associated courses.
     */
    @Override
    public TeacherResponseDto getTeacherById(String teacherId) {
        Teacher foundedTeacher = teacherRepository.getTeacherByIdWithCourses(teacherId);
        return modelMapper.map(foundedTeacher, TeacherResponseDto.class);
    }

    /**
     * Retrieves a teacher's courses by ID from the repository.
     * Returns the TeacherCourseResponseDto representing the courses taught by the teacher.
     */
    @Override
    public TeacherCourseResponseDto getTeacherCourses(String teacherId) {
        return null;
    }

    /**
     * Updates a teacher's details based on the provided TeacherRequestDto.
     * Returns the updated teacher's details as a response DTO.
     */
    @Override
    public TeacherResponseDto updateTeacher(String teacherId, TeacherRequestDto teacherRequest) {
        Teacher foundedTeacher = findTeacherById(teacherId);
        foundedTeacher.setFirstName(teacherRequest.getFirstName());
        foundedTeacher.setLastName(teacherRequest.getLastName());
        foundedTeacher.setTeacherEmail(teacherRequest.getTeacherEmail());

        Teacher updatedTeacher = teacherRepository.updateTeacher(teacherId, foundedTeacher);
        return modelMapper.map(updatedTeacher, TeacherResponseDto.class);
    }

    /**
     * Deletes a teacher by ID via the repository.
     */
    @Override
    public void deleteTeacher(String teacherId) {
        Teacher foundedTeacher = findTeacherById(teacherId);
        teacherRepository.deleteTeacher(teacherId);
    }

    /**
     * Method to find a teacher by ID via the repository.
     * Returns the found Teacher entity.
     */
    private Teacher findTeacherById(String teacherId) {
        return teacherRepository.getTeacherById(teacherId);
    }
}