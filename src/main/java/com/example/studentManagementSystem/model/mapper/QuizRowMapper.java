package com.example.studentManagementSystem.model.mapper;

import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class QuizRowMapper implements RowMapper<Quiz> {

    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setQuizId(rs.getString("quiz_id"));
        quiz.setTitle(rs.getString("quiz_title"));

        Course course = new Course();
        course.setCourseId(rs.getString("course_id"));
        course.setCourseName(rs.getString("course_name"));
        quiz.setCourse(course);
        return quiz;
    }
}
