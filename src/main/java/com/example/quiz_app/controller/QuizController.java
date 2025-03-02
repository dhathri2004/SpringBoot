package com.example.quiz_app.controller;

import com.example.quiz_app.model.Question;
import com.example.quiz_app.model.QuizSubmission;
import com.example.quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*") // Allow frontend access
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return quizService.getQuestions();
    }

    @PostMapping("/submit")
    public String evaluateQuiz(@RequestBody QuizSubmission submission) {
        int score = quizService.evaluateQuiz(submission);
        return "Your score: " + score + " / " + quizService.getQuestions().size();
    }
}
