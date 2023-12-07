package com.example.studentManagementSystem.repository;

import com.example.studentManagementSystem.model.Quiz;

import java.util.List;

public interface QuizRepository {
    Quiz createQuiz(Quiz quiz);

    List<Quiz> getAllQuizzes(String courseId);

    Quiz getQuizById(String courseId, String quizId);

    Quiz updateQuiz(String quizId, Quiz quiz);

    void deleteQuiz(String courseId, String quizId);
}
