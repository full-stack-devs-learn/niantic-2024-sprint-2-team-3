package com.nianti.controllers;

import com.nianti.models.Question;
import com.nianti.services.QuestionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController
{
    QuestionDao questionDao = new QuestionDao();

    @GetMapping("/quiz/{quizId}/{questionId}")
    public String displayQuestion(Model model, @PathVariable int quizId, @PathVariable int questionId)
    {
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);

        model.addAttribute("questions",questions);

        return "quiz/question";

    }
}
