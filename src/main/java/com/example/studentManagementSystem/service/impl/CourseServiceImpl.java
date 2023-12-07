package com.example.studentManagementSystem.service.impl;

import com.example.studentManagementSystem.exception.ResourceNotFoundedException;
import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Student;
import com.example.studentManagementSystem.model.Teacher;
import com.example.studentManagementSystem.model.dto.request.CourseRequestDto;
import com.example.studentManagementSystem.model.dto.response.CourseResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseStudentResponseDto;
import com.example.studentManagementSystem.model.dto.response.CourseTeacherResponseDto;
import com.example.studentManagementSystem.repository.CourseRepository;
import com.example.studentManagementSystem.repository.StudentRepository;
import com.example.studentManagementSystem.repository.TeacherRepository;
import com.example.studentManagementSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new course based on the provided CourseRequestDto.
     * Returns the created course response along with the assigned teacher.
     */
    @Override
    public CourseTeacherResponseDto createCourse(CourseRequestDto courseRequest) {
        Course course = modelMapper.map(courseRequest, Course.class);
        course.generateId();
        if (teacherRepository.getTeacherById(courseRequest.getTeacherId()) == null) {
            throw new ResourceNotFoundedException("Not Founded Teacher with id: " + courseRequest.getTeacherId());
        }
        Course savedCourse = courseRepository.createCourse(course);
        return modelMapper.map(savedCourse, CourseTeacherResponseDto.class);
    }

    /**
     * Retrieves all courses from the database.
     * Returns a list of CourseResponseDto containing course details.
     */
    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courses = courseRepository.getAllCourses();
        return courses.stream().map(c -> modelMapper.map(c, CourseResponseDto.class)).toList();
    }

    /**
     * Retrieves a course by ID from the database along with its teacher.
     * Returns the CourseTeacherResponseDto containing course details with the assigned teacher.
     */
    @Override
    public CourseTeacherResponseDto getCourseById(String courseId) {
        Course foundedCourse = courseRepository.getCourseByIdWithQuizzes(courseId);
        return modelMapper.map(foundedCourse, CourseTeacherResponseDto.class);
    }

    /**
     * Updates a course's details in the database based on the provided CourseRequestDto.
     * Returns the updated CourseTeacherResponseDto along with the assigned teacher.
     */
    @Override
    public CourseTeacherResponseDto updateCourse(String courseId, CourseRequestDto courseRequest) {
        Course foundedCourse = findCourseById(courseId);
        foundedCourse.setCourseName(courseRequest.getCourseName());
        Teacher teacher = teacherRepository.getTeacherById(courseRequest.getTeacherId());
        if (teacher == null) {
            throw new ResourceNotFoundedException("Not Founded Teacher with id: " + courseRequest.getTeacherId());
        }
        foundedCourse.setTeacher(teacher);
        Course updatedCourse = courseRepository.updateCourse(courseId, foundedCourse);
        return modelMapper.map(updatedCourse, CourseTeacherResponseDto.class);
    }

    /**
     * Deletes a course from the database by ID.
     */
    @Override
    public void deleteCourse(String courseId) {
        Course foundedCourse = findCourseById(courseId);
        courseRepository.deleteCourse(courseId);
    }

    // Method to find a course by ID in the database
    private Course findCourseById(String courseId) {
        return courseRepository.getCourseById(courseId);
    }

    /**
     * Assigns students to a course in the database by their IDs.
     * Returns the CourseStudentResponseDto containing details of the course along with enrolled students.
     */
    @Override
    public CourseStudentResponseDto assignStudentsToCourse(String courseId, List<String> studentsId) {
        Course foundedCourse = findCourseById(courseId);
        Set<Student> students = new HashSet<>();
        studentsId.forEach(studentId -> {
            Student student1 = studentRepository.getStudentById(studentId);
            students.add(student1);
        });

        Course result = courseRepository.assignStudentsToCourse(students, courseId);
        result.setStudents(students);

        return modelMapper.map(result, CourseStudentResponseDto.class);
    }
}
