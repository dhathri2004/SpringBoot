package com.example.quiz_app.model;

public class Question {
    private int id;
    private String question;
    private String[] options;
    private int correctOption; // Index of the correct answer
    private String category;

    public Question() {}

    public Question(int id, String question, String[] options, int correctOption, String category) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.category = category;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String getCategory() {
        return category;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
