package com.example.quiz_app.service;

import com.example.quiz_app.model.AdminQuestion; // Use AdminQuestion instead of Question
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private static final String FILE_PATH = "data/questions.json"; 
    private final ObjectMapper objectMapper; // Inject ObjectMapper

    public AdminService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void addQuestion(AdminQuestion newQuestion) { // Change to AdminQuestion
        File file = new File(FILE_PATH);

        try {
            // Ensure directory exists
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            List<AdminQuestion> questionList;
            if (file.exists() && file.length() > 0) {
                questionList = objectMapper.readValue(file, objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, AdminQuestion.class));
            } else {
                questionList = new ArrayList<>();
            }

            // Assign a new ID and add the question
            newQuestion.setId(questionList.size() + 1);
            questionList.add(newQuestion);

            // Write updated list back to JSON
            objectMapper.writeValue(file, questionList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing to JSON file");
        }
    }
}
