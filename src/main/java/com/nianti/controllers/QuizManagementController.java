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
        List<Boolean> liveOptions = new ArrayList<>()
        {{
            add(true);
            add(false);
        }};

        model.addAttribute("quiz", new Quiz());
        model.addAttribute("liveOptions", liveOptions);

        return "quiz-management/add-quiz";
    }

    @PostMapping("/quiz/add")
    public String addQuiz(Model model, @Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result)
    {
        if (result.hasErrors())
        {
            model.addAttribute("isInvalid", true);

            return "quiz-management/add-quiz";
        }

        quizDao.addQuiz(quiz);

        return "redirect:/quizzes";
    }

}
