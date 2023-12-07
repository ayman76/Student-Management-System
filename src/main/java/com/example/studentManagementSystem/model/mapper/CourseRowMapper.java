package com.example.studentManagementSystem.model.mapper;

import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getString("course_id"));
        course.setCourseName(rs.getString("course_name"));

        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getString("teacher_id"));
        teacher.setFirstName(rs.getString("teacher_firstName"));
        teacher.setLastName(rs.getString("teacher_lastName"));
        teacher.setTeacherEmail(rs.getString("teacher_email"));
        course.setTeacher(teacher);
        return course;
    }
}
