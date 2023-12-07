package com.example.studentManagementSystem.model.mapper;

import com.example.studentManagementSystem.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class TeacherRowMapper implements RowMapper<Teacher> {

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getString("id"));
        teacher.setFirstName(rs.getString("firstName"));
        teacher.setLastName(rs.getString("lastName"));
        teacher.setTeacherEmail(rs.getString("email"));

        return teacher;
    }
}
