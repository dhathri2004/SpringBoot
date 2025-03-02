package com.example.quiz_app.service;

import com.example.quiz_app.model.Question;
import com.example.quiz_app.model.QuizSubmission;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class QuizService {
    private static final String FILE_PATH = "data/questions.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Question> getQuestions() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of(); // Return an empty list if no questions found
    }

    public int evaluateQuiz(QuizSubmission submission) {
        List<Question> questions = getQuestions();
        List<Integer> answers = submission.getAnswers();
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (i < answers.size() && questions.get(i).getCorrectOption() == answers.get(i)) {
                score++;
            }
        }

        return score;
    }
}
