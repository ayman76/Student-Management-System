package com.example.studentManagementSystem.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCourseResponseDto {
    private String teacherId;
    private String firstName;
    private String lastName;
    private String teacherEmail;
    private List<CourseResponseDto> courses;

}
