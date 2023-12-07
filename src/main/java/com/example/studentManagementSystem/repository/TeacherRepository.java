package com.example.studentManagementSystem.repository;

import com.example.studentManagementSystem.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teacherRepository")
public interface TeacherRepository {
    Teacher createTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    Teacher getTeacherById(String teacherId);

    Teacher getTeacherCourses(String teacherId);

    Teacher updateTeacher(String teacherId, Teacher teacher);

    Teacher getTeacherByIdWithCourses(String teacherId);

    void deleteTeacher(String teacherId);
}
