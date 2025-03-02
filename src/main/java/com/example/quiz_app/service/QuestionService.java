package com.example.quiz_app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.quiz_app.model.Question;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final String FILE_PATH = "data/questions.json";
    private List<Question> questions;

    public QuestionService() {
        loadQuestions();
    }

    // Load questions from JSON
    private void loadQuestions() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            questions = mapper.readValue(new File(FILE_PATH), new TypeReference<List<Question>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all questions but hide correct answer
    public List<Question> getAllQuestions() {
        return questions.stream().map(q -> {
            Question maskedQuestion = new Question();
            maskedQuestion.setId(q.getId());
            maskedQuestion.setQuestion(q.getQuestion());
            maskedQuestion.setOptions(q.getOptions());
            return maskedQuestion; // Correct option is hidden
        }).collect(Collectors.toList());
    }

    // Validate answers and return score
    public int calculateScore(List<Integer> userAnswers) {
        int score = 0;
        for (int i = 0; i < userAnswers.size(); i++) {
            if (questions.get(i).getCorrectOption() == userAnswers.get(i)) {
                score++;
            }
        }
        return score;
    }
}
