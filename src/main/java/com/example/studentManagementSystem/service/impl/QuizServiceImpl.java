package com.example.studentManagementSystem.service.impl;

import com.example.studentManagementSystem.exception.ResourceNotFoundedException;
import com.example.studentManagementSystem.model.Course;
import com.example.studentManagementSystem.model.Quiz;
import com.example.studentManagementSystem.model.dto.request.QuizRequestDto;
import com.example.studentManagementSystem.model.dto.response.QuizResponseDto;
import com.example.studentManagementSystem.repository.CourseRepository;
import com.example.studentManagementSystem.repository.QuizRepository;
import com.example.studentManagementSystem.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new quiz based on the provided QuizRequestDto.
     * Returns the created quiz response along with the associated course.
     */
    @Override
    public QuizResponseDto createQuiz(QuizRequestDto quizRequest) {
        Quiz quiz = modelMapper.map(quizRequest, Quiz.class);
        quiz.generateId();
        if (courseRepository.getCourseById(quizRequest.getCourseId()) == null) {
            throw new ResourceNotFoundedException("Not Founded Course with id: " + quizRequest.getCourseId());
        }
        Quiz savedQuiz = quizRepository.createQuiz(quiz);
        return modelMapper.map(savedQuiz, QuizResponseDto.class);
    }

    /**
     * Retrieves all quizzes associated with a specific course from the database.
     * Returns a list of QuizResponseDto objects for the given course ID.
     */
    @Override
    public List<QuizResponseDto> getAllQuizzes(String courseId) {
        List<Quiz> quizzes = quizRepository.getAllQuizzes(courseId);
        return quizzes.stream().map(q -> modelMapper.map(q, QuizResponseDto.class)).toList();
    }

    /**
     * Retrieves a quiz by its ID and associated course ID from the database.
     * Returns the QuizResponseDto object corresponding to the provided IDs.
     */
    @Override
    public QuizResponseDto getQuizById(String courseId, String quizId) {
        Quiz foundedQuiz = findQuizById(courseId, quizId);
        return modelMapper.map(foundedQuiz, QuizResponseDto.class);
    }

    /**
     * Updates a quiz's details in the database based on the provided QuizRequestDto and ID.
     * Returns the updated QuizResponseDto object.
     */
    @Override
    public QuizResponseDto updateQuiz(String quizId, QuizRequestDto quizRequest) {
        Quiz foundedQuiz = findQuizById(quizRequest.getCourseId(), quizId);
        foundedQuiz.setTitle(quizRequest.getTitle());
        Course course = courseRepository.getCourseById(quizRequest.getCourseId());
        if (course == null) {
            throw new ResourceNotFoundedException("Not Founded Course with id: " + quizRequest.getCourseId());
        }
        foundedQuiz.setCourse(course);
        Quiz updatedQuiz = quizRepository.updateQuiz(quizId, foundedQuiz);
        return modelMapper.map(updatedQuiz, QuizResponseDto.class);
    }

    /**
     * Deletes a quiz from the database by its ID and associated course ID.
     */
    @Override
    public void deleteQuiz(String courseId, String quizId) {
        Quiz foundedQuiz = findQuizById(courseId, quizId);
        quizRepository.deleteQuiz(courseId, quizId);
    }

    /**
     * Helper method to find a quiz by its ID and associated course ID.
     * Returns the found Quiz object.
     */
    private Quiz findQuizById(String courseId, String quizId) {
        return quizRepository.getQuizById(courseId, quizId);
    }

}
