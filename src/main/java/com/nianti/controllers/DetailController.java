package com.nianti.controllers;

import com.nianti.models.Question;
import com.nianti.models.Quiz;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DetailController
{
    QuestionDao questionDao = new QuestionDao();
    QuizDao quizDao = new QuizDao();

    @GetMapping("/quiz/{quizId}/details")
        public String getQuizDetails(Model model, @PathVariable int quizId)
        {
            Quiz quiz = quizDao.getQuizById(quizId);
            List<Question> questions = questionDao.getQuestionByQuizId(quizId);

            model.addAttribute("quiz", quiz);
            model.addAttribute("questions",questions);

            return "details/quiz-details";
        }




}
