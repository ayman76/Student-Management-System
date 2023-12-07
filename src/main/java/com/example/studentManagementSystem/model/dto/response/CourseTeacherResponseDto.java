package com.example.studentManagementSystem.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTeacherResponseDto {

    private String courseId;
    private String courseName;
    private TeacherResponseDto teacher;
}
