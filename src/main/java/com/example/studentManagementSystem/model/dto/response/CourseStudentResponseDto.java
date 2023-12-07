package com.example.studentManagementSystem.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentResponseDto {

    private String courseId;
    private String courseName;
    private Set<StudentResponseDto> students;
}
