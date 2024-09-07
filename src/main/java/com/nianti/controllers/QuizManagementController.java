package com.nianti.controllers;

import com.nianti.models.Quiz;
import com.nianti.services.QuizDao;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/quiz/add")
    public String addQuiz(Model model)
    {
        model.addAttribute("quiz", new Quiz());
        model.addAttribute("action", "add");

        return "quiz-management/add-edit-quiz";
    }

    @PostMapping("/quiz/add")
    public String addQuiz(Model model, @Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result)
    {
        if (result.hasErrors())
        {
            model.addAttribute("isInvalid", true);

            return "quiz-management/add-edit-quiz";
        }

        quizDao.addQuiz(quiz);

        return "redirect:/quizzes";
    }

    @GetMapping("/quiz/{id}/edit")
    public String updateQuiz(Model model, @PathVariable int id)
    {
        Quiz quiz = quizDao.getQuizById(id);

        model.addAttribute("quiz", quiz);
        model.addAttribute("action", "edit");

        return "quiz-management/add-edit-quiz";
    }

    @PostMapping("/quiz/{id}/edit")
    public String updateQuiz(Model model, @PathVariable int id, @Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result)
    {
        if (result.hasErrors())
        {
            model.addAttribute("isInvalid", true);

            return "quiz-management/add-edit-quiz";
        }

        quiz.setQuizId(id);
        quizDao.editQuiz(quiz);

        return "redirect:/quizzes";
    }

}
