package com.example.studentManagementSystem.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {

    private String studentId;
    private String firstName;
    private String lastName;
    private String studentEmail;
    private int studentAge;
}
