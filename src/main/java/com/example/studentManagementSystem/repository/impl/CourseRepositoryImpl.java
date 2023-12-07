package com.example.studentManagementSystem.repository.impl;

import com.example.studentManagementSystem.exception.ResourceNotFoundedException;
import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Quiz;
import com.example.studentManagementSystem.model.Student;
import com.example.studentManagementSystem.model.Teacher;
import com.example.studentManagementSystem.model.mapper.CourseRowMapper;
import com.example.studentManagementSystem.repository.CourseRepository;
import com.example.studentManagementSystem.repository.QuizRepository;
import com.example.studentManagementSystem.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Course createCourse(Course course) {
        try {
            String sql = "insert into course (id, courseName, teacher_id)" + "values (?, ?, ?)";
            jdbcTemplate.update(sql, course.getCourseId(), course.getCourseName(), course.getTeacher().getTeacherId());
            TeacherRepository teacherRepository = new TeacherRepositoryImpl(jdbcTemplate);
            Teacher teacher = teacherRepository.getTeacherById(course.getTeacher().getTeacherId());
            course.setTeacher(teacher);
            return course;
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while saving course data: " + ex.getMessage());
        }

    }

    /**
     * Retrieves all courses from the database along with their respective teachers.
     * Returns a list of Course entities.
     */
    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT c.id AS course_id, c.courseName AS course_name, t.id AS teacher_id, t.firstName AS teacher_firstName" +
                ", t.lastName AS teacher_lastName, t.email As teacher_email " +
                "FROM course c " +
                "LEFT JOIN teacher t ON c.teacher_id = t.id";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }


    /**
     * Retrieves a course by ID from the database along with its respective teacher.
     * Returns the Course entity if found, otherwise throws a ResourceNotFoundedException.
     */
    @Override
    public Course getCourseById(String courseId) {
        try {
            String sql = "SELECT c.id AS course_id, c.courseName AS course_name, t.id AS teacher_id, t.firstName AS teacher_firstName" +
                    ", t.lastName AS teacher_lastName, t.email As teacher_email " +
                    "FROM course c " +
                    "LEFT JOIN teacher t ON c.teacher_id = t.id" +
                    " WHERE c.id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{courseId}, new CourseRowMapper());
        } catch (Exception ex) {
            throw new ResourceNotFoundedException("Not Founded Course with id: " + courseId);
        }

    }

    @Override
    public Course updateCourse(String courseId, Course course) {
        Course founeded_course = getCourseById(courseId);
        if (founeded_course == null) {
            return null;
        }
        try {
            String sql = "UPDATE course SET courseName = ?, teacher_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, course.getCourseName(), course.getTeacher().getTeacherId(), course.getCourseId());
            return course;

        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while updating course data: " + ex.getMessage());
        }

    }

    @Override
    public void deleteCourse(String courseId) {
        Course founeded_course = getCourseById(courseId);
        if (founeded_course == null) {
            return;
        }
        try {
            // Delete quizzes associated with the course
            String deleteQuizzesSql = "DELETE FROM quiz WHERE course_id = ?";
            jdbcTemplate.update(deleteQuizzesSql, courseId);

            // Delete enrolled students from the course
            String deleteCourseStudentSql = "DELETE FROM course_student WHERE course_id = ?";
            jdbcTemplate.update(deleteCourseStudentSql, courseId);

            // Delete the course itself
            String sql = "DELETE FROM course WHERE id = ?";
            jdbcTemplate.update(sql, courseId);

        } catch (Exception ex) {
            // Handle any exception that occurs during deletion
            throw new RuntimeException("Error occurred while deleting course data: " + ex.getMessage());
        }
    }

    @Override
    public Course getCourseByIdWithQuizzes(String courseId) {
        Course founeded_course = getCourseById(courseId);

        QuizRepository quizRepository = new QuizRepositoryImpl(jdbcTemplate);
        List<Quiz> courseQuizzes = quizRepository.getAllQuizzes(courseId).stream().filter(quiz -> quiz.getCourse().getCourseId().equals(courseId)).toList();
        founeded_course.setQuizzes(courseQuizzes);
        return founeded_course;
    }

    @Override
    public Course assignStudentsToCourse(Set<Student> students, String courseId) {
        Course founeded_course = getCourseById(courseId);

        students.forEach(student -> {
            String sql = "insert into course_student (course_id, student_id) values (?, ?)";
            jdbcTemplate.update(sql, new Object[]{courseId, student.getStudentId()});
            founeded_course.getStudents().add(student);
        });
        return founeded_course;
    }
}
