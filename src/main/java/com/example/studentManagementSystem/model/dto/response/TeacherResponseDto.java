package com.example.studentManagementSystem.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponseDto {

    private String teacherId;
    private String firstName;
    private String lastName;
    private String teacherEmail;
}
