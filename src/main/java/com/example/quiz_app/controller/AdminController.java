package com.example.quiz_app.controller;

import com.example.quiz_app.model.AdminQuestion;
import com.example.quiz_app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*") // Allow frontend to access this API
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-question")
    public String addQuestion(@RequestBody AdminQuestion question) { // Change to AdminQuestion
        adminService.addQuestion(question);
        return "Question added successfully!";
    }
}
