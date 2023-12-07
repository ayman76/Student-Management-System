package com.example.studentManagementSystem.controller;

import com.example.studentManagementSystem.model.dto.request.QuizRequestDto;
import com.example.studentManagementSystem.model.dto.response.QuizResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface QuizController {

    ResponseEntity<QuizResponseDto> createQuiz(@PathVariable("courseId") String courseId, @RequestBody QuizRequestDto quizRequest);

    ResponseEntity<List<QuizResponseDto>> getAllQuizzes(@PathVariable("courseId") String courseId);

    ResponseEntity<QuizResponseDto> getQuizById(@PathVariable("courseId") String courseId, @PathVariable("quizId") String quizId);

    ResponseEntity<QuizResponseDto> updateQuiz(@PathVariable("courseId") String courseId, @PathVariable("quizId") String quizId, @RequestBody QuizRequestDto quizRequest);

    ResponseEntity<Void> deleteQuiz(@PathVariable("courseId") String courseId, @PathVariable("quizId") String quizId);
}
