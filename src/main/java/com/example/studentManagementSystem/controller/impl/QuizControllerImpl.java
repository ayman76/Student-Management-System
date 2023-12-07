package com.example.studentManagementSystem.controller.impl;

import com.example.studentManagementSystem.controller.QuizController;
import com.example.studentManagementSystem.model.dto.request.QuizRequestDto;
import com.example.studentManagementSystem.model.dto.response.QuizResponseDto;
import com.example.studentManagementSystem.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/quizzes")
@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<QuizResponseDto> createQuiz(@PathVariable("courseId") String courseId, @RequestBody QuizRequestDto quizRequest) {
        quizRequest.setCourseId(courseId);
        return new ResponseEntity<>(quizService.createQuiz(quizRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizResponseDto>> getAllQuizzes(@PathVariable("courseId") String courseId) {
        return new ResponseEntity<>(quizService.getAllQuizzes(courseId), HttpStatus.OK);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResponseDto> getQuizById(@PathVariable("courseId") String courseId, @PathVariable("quizId") String quizId) {
        return new ResponseEntity<>(quizService.getQuizById(courseId, quizId), HttpStatus.OK);
    }

    @PutMapping("/{quizId}/update")
    public ResponseEntity<QuizResponseDto> updateQuiz(@PathVariable("courseId") String courseId, @PathVariable("quizId") String quizId, @RequestBody QuizRequestDto quizRequest) {
        quizRequest.setCourseId(courseId);
        return new ResponseEntity<>(quizService.updateQuiz(quizId, quizRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{quizId}/delete")
    public ResponseEntity<Void> deleteQuiz(@PathVariable("courseId") String courseId, @PathVariable("quizId") String quizId) {
        quizService.deleteQuiz(courseId, quizId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
