package com.example.studentManagementSystem.repository;

import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Student;

import java.util.List;
import java.util.Set;

public interface CourseRepository {
    Course createCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(String courseId);

    Course updateCourse(String courseId, Course course);

    void deleteCourse(String courseId);

    Course getCourseByIdWithQuizzes(String courseId);

    Course assignStudentsToCourse(Set<Student> students, String courseId);
}
