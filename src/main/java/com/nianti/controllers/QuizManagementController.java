package com.nianti.controllers;

import com.nianti.models.Quiz;
import com.nianti.services.QuizDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuizManagementController
{
   private QuizDao quizDao = new QuizDao();

    @GetMapping("/quizzes")
    public String getQuizzes(Model model)
    {
        var quizzes = quizDao.getAllQuizzes();

        model.addAttribute("quizzes", quizzes);

        return "quiz-management/index";
    }




}
