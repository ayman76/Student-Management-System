package com.example.studentManagementSystem.service;

import com.example.studentManagementSystem.model.dto.request.QuizRequestDto;
import com.example.studentManagementSystem.model.dto.response.QuizResponseDto;

import java.util.List;

public interface QuizService {

    QuizResponseDto createQuiz(QuizRequestDto quizRequest);

    List<QuizResponseDto> getAllQuizzes(String courseId);

    QuizResponseDto getQuizById(String courseId, String quizId);

    QuizResponseDto updateQuiz(String quizId, QuizRequestDto quizRequest);

    void deleteQuiz(String courseId, String quizId);
}
