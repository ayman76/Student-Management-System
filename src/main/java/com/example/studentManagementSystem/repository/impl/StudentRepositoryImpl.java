package com.example.studentManagementSystem.repository.impl;

import com.example.studentManagementSystem.exception.ResourceNotFoundedException;
import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Student;
import com.example.studentManagementSystem.model.mapper.StudentRowMapper;
import com.example.studentManagementSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Student createStudent(Student student) {
        try {
            String sql = "insert into student (id, firstName, lastName, studentAge, email)\n" + "values (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, student.getStudentId(), student.getFirstName(), student.getLastName(), student.getStudentAge(), student.getStudentEmail());
            return student;
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while saving student data: " + ex.getMessage());
        }

    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    @Override
    public Student getStudentById(String studentId) {
        try {
            String sql = "SELECT * FROM student where id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{studentId}, new StudentRowMapper());
        } catch (Exception ex) {
            throw new ResourceNotFoundedException("Not Founded Student with id: " + studentId);
        }

    }

    @Override
    public Student getStudentCourses(String studentId) {
        Student foundedStudent = getStudentById(studentId);
        try {
            // Retrieves associated courses from the database and adds them to the Student entity
            String sql = "SELECT id, courseName FROM course where id in (SELECT course_id from course_student where student_id = ?)";
            jdbcTemplate.query(sql,
                    new Object[]{studentId},
                    rs -> {
                        foundedStudent.getCourses().add(new Course().builder().courseId(rs.getString("id")).courseName(rs.getString("courseName")).build());
                    });
            return foundedStudent;
        } catch (Exception ex) {
            throw new ResourceNotFoundedException("Not Founded Student with id: " + studentId);
        }

    }


    @Override
    public Student updateStudent(String studentId, Student student) {
        Student founeded_student = getStudentById(studentId);
        if (founeded_student == null) {
            return null;
        }
        String sql = "UPDATE student SET firstName = ?, lastName = ?, email = ?, studentAge = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getStudentEmail(), student.getStudentAge(), student.getStudentId());
        return student;
    }

    @Override
    public void deleteStudent(String studentId) {
        Student founeded_student = getStudentById(studentId);
        if (founeded_student == null) {
            return;
        }
        try {
            // Deletes associated course-student relationships
            String deleteCourseStudentSql = "DELETE FROM course_student WHERE student_id = ?";
            jdbcTemplate.update(deleteCourseStudentSql, studentId);

            String sql = "DELETE FROM student WHERE id = ?";
            jdbcTemplate.update(sql, studentId);
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while deleting student data: " + ex.getMessage());
        }

    }
}
