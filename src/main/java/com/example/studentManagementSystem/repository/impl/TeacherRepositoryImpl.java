package com.example.studentManagementSystem.repository.impl;

import com.example.studentManagementSystem.exception.ResourceNotFoundedException;
import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Teacher;
import com.example.studentManagementSystem.model.mapper.TeacherRowMapper;
import com.example.studentManagementSystem.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        try {
            String sql = "insert into teacher (id, firstName, lastName, email)" + "values (?, ?, ?, ?)";
            jdbcTemplate.update(sql, teacher.getTeacherId(), teacher.getFirstName(), teacher.getLastName(), teacher.getTeacherEmail());
            return teacher;
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while saving teacher data: " + ex.getMessage());
        }

    }

    @Override
    public List<Teacher> getAllTeachers() {
        String sql = "SELECT * FROM teacher";
        return jdbcTemplate.query(sql, new TeacherRowMapper());
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        try {
            String sql = "SELECT * FROM teacher where id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{teacherId}, new TeacherRowMapper());
        } catch (Exception ex) {
            throw new ResourceNotFoundedException("Not Founded Teacher with id: " + teacherId);
        }

    }

    @Override
    public Teacher getTeacherCourses(String teacherId) {
        Teacher foundedTeacher = getTeacherById(teacherId);
        try {
            String sql = "SELECT id, courseName FROM course where course.teacher_id = ?";
            jdbcTemplate.query(sql,
                    new Object[]{teacherId},
                    rs -> {
                        foundedTeacher.getCourses().add(new Course().builder().courseId(rs.getString("id")).courseName(rs.getString("courseName")).build());
                    });
            return foundedTeacher;
        } catch (Exception ex) {
            throw new ResourceNotFoundedException("Not Founded Teacher with id: " + teacherId);
        }

    }

    @Override
    public Teacher updateTeacher(String teacherId, Teacher teacher) {
        Teacher founeded_teacher = getTeacherById(teacherId);
        if (founeded_teacher == null) {
            return null;
        }
        try {
            String sql = "UPDATE teacher SET firstName = ?, lastName = ?, email = ? WHERE id = ?";
            jdbcTemplate.update(sql, teacher.getFirstName(), teacher.getLastName(), teacher.getTeacherEmail(), teacher.getTeacherId());
            return teacher;

        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while updating teacher data: " + ex.getMessage());
        }

    }

    @Override
    public void deleteTeacher(String teacherId) {
        Teacher founeded_teacher = getTeacherById(teacherId);
        if (founeded_teacher == null) {
            return;
        }

        try {

            String deleteQuizzesQuery = "DELETE FROM quiz WHERE course_id IN (SELECT id FROM course WHERE teacher_id = ?)";
            jdbcTemplate.update(deleteQuizzesQuery, teacherId);

            String deleteCoursesSql = "Delete from course where teacher_id = ?";
            jdbcTemplate.update(deleteCoursesSql, teacherId);

            String sql = "DELETE FROM teacher WHERE id = ?";
            jdbcTemplate.update(sql, teacherId);

        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while deleting teacher data: " + ex.getMessage());

        }
    }

    @Override
    public Teacher getTeacherByIdWithCourses(String teacherId) {
        return null;
    }
}
