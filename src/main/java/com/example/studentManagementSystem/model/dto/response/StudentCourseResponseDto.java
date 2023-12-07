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
public class StudentCourseResponseDto {
    private String studentId;
    private String firstName;
    private String lastName;
    private String studentEmail;
    private int age;
    private List<CourseResponseDto> courses;

}
