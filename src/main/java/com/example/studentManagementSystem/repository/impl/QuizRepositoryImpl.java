package com.example.studentManagementSystem.repository.impl;

import com.example.studentManagementSystem.exception.ResourceNotFoundedException;
import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Quiz;
import com.example.studentManagementSystem.model.mapper.QuizRowMapper;
import com.example.studentManagementSystem.repository.CourseRepository;
import com.example.studentManagementSystem.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RequiredArgsConstructor
public class QuizRepositoryImpl implements QuizRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Creates a new quiz based on the provided Quiz object.
     * Returns the created quiz response along with the associated course.
     */
    @Override
    public Quiz createQuiz(Quiz quiz) {
        try {
            String sql = "insert into quiz (id, title, course_id)" + "values (?, ?, ?)";
            jdbcTemplate.update(sql, quiz.getQuizId(), quiz.getTitle(), quiz.getCourse().getCourseId());
            CourseRepository courseRepository = new CourseRepositoryImpl(jdbcTemplate);
            Course course = courseRepository.getCourseById(quiz.getCourse().getCourseId());
            quiz.setCourse(course);
            return quiz;
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while saving quiz data: " + ex.getMessage());
        }

    }

    /**
     * Retrieves all quizzes associated with a specific course from the database.
     * Returns a list of Quiz entities for the given course ID.
     */
    @Override
    public List<Quiz> getAllQuizzes(String courseId) {
        String sql = "SELECT q.id AS quiz_id, q.title AS quiz_title, c.id AS course_id, c.courseName AS course_name" +
                " FROM quiz q " +
                "LEFT JOIN course c ON q.course_id = c.id"
                + " WHERE q.course_id = ?";
        return jdbcTemplate.query(sql, new QuizRowMapper(), courseId);
    }

    /**
     * Retrieves a quiz by its ID and associated course ID from the database.
     * Returns the Quiz object corresponding to the provided IDs.
     */
    @Override
    public Quiz getQuizById(String courseId, String quizId) {
        try {
            String sql = "SELECT q.id AS quiz_id, q.title AS quiz_title, c.id AS course_id, c.courseName AS course_name" +
                    " FROM quiz q " +
                    "LEFT JOIN course c ON q.course_id = c.id" +
                    " WHERE q.id = ? and q.course_id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{quizId, courseId}, new QuizRowMapper());
        } catch (Exception ex) {
            throw new ResourceNotFoundedException("Not Founded Quiz with id: " + quizId);
        }

    }

    @Override
    public Quiz updateQuiz(String quizId, Quiz quiz) {
        Quiz founeded_quiz = getQuizById(quiz.getCourse().getCourseId(), quizId);
        if (founeded_quiz == null) {
            return null;
        }
        try {
            String sql = "UPDATE quiz SET title = ?, course_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, quiz.getTitle(), quiz.getCourse().getCourseId(), quiz.getQuizId());
            return quiz;

        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while updating quiz data: " + ex.getMessage());
        }

    }

    @Override
    public void deleteQuiz(String courseId, String quizId) {
        Quiz founeded_quiz = getQuizById(courseId, quizId);
        if (founeded_quiz == null) {
            return;
        }
        try {
            String sql = "DELETE FROM quiz WHERE id = ? and course_id = ?";
            jdbcTemplate.update(sql, quizId, courseId);

        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while deleting quiz data: " + ex.getMessage());

        }


    }
}
