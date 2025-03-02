package com.example.quiz_app.model;

import java.util.List;

public class QuizSubmission {
    private List<Integer> answers; // Stores selected options (index-based)
    
    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
