package com.example.studentManagementSystem.model.mapper;

import com.example.studentManagementSystem.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getString("id"));
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        student.setStudentEmail(rs.getString("email"));
        student.setStudentAge(rs.getInt("studentAge"));

        return student;
    }
}
