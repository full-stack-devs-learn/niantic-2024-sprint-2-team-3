package com.nianti.controllers;

import com.nianti.models.Quiz;
import com.nianti.services.QuizDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizPageController {
    QuizDao quizDao = new QuizDao();

    @GetMapping("/quiz/{quizId}")
    public String getQuizTitle(Model model, @PathVariable int quizId)
    {
        Quiz quiz = quizDao.getQuizById(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("title", String.format("Take Quiz: %s", quiz.getTitle()));

        return "quiz-page/quiz-page";
    }


}
