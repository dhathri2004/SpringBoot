package com.example.quiz_app.model;

import java.util.List;

public class AdminQuestion {
    private int id;
    private String question;
    private List<String> options;
    private int correctOption;
    private String category;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public int getCorrectOption() { return correctOption; }
    public void setCorrectOption(int correctOption) { this.correctOption = correctOption; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
