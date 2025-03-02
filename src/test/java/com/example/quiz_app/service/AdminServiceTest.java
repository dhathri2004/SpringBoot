package com.example.quiz_app.service;

import com.example.quiz_app.model.AdminQuestion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito support
public class AdminServiceTest {

    private static final String FILE_PATH = "data/questions.json";

    @Mock
    private ObjectMapper objectMapper; // Mock ObjectMapper to avoid file I/O

    @InjectMocks
    private AdminService adminService; // Injects the mock ObjectMapper

    private List<AdminQuestion> mockQuestions;

    @BeforeEach
    public void setup() {
        mockQuestions = new ArrayList<>();
    }

    @Test
    public void testAddQuestion() throws IOException {
        // Arrange: Simulate existing questions in JSON
        List<AdminQuestion> existingQuestions = new ArrayList<>();
        existingQuestions.add(new AdminQuestion()); // Empty object since constructor is missing

        AdminQuestion newQuestion = new AdminQuestion();
        newQuestion.setId(2);
        newQuestion.setQuestion("What is 5 + 3?");
        newQuestion.setOptions(Arrays.asList("5", "6", "7", "8")); // Works with Java 8+
        newQuestion.setCorrectOption(3);
        newQuestion.setCategory("Math");

        File file = new File(FILE_PATH); // We mock file operations

        // Properly mock TypeFactory and CollectionType
        TypeFactory mockTypeFactory = mock(TypeFactory.class);
        CollectionType mockCollectionType = mock(CollectionType.class);
        when(objectMapper.getTypeFactory()).thenReturn(mockTypeFactory);
        when(mockTypeFactory.constructCollectionType(List.class, AdminQuestion.class)).thenReturn(mockCollectionType);

        // Mock JSON reading from file
        when(objectMapper.readValue(any(File.class), eq(mockCollectionType)))
                .thenReturn(existingQuestions);

        // Act: Add the new question
        adminService.addQuestion(newQuestion);

        // Assert: Verify JSON write operation
        verify(objectMapper, times(1)).writeValue(any(File.class), anyList());
    }
}
