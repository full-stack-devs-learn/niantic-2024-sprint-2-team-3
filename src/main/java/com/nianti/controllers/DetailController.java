package com.nianti.controllers;

import com.nianti.models.Question;
import com.nianti.models.Quiz;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/quiz/{quizId}/question/add")
    public String addQuestion(Model model, @PathVariable int quizId)
    {
        Quiz quiz = quizDao.getQuizById(quizId);
        List<Quiz> quizzes = quizDao.getAllQuizzes();

        model.addAttribute("question", new Question());
        model.addAttribute("quiz", quiz);
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("action", "add");

        return "details/add-edit-question";
    }

    @PostMapping("/quiz/{quizId}/question/add")
    public String addQuestion(@ModelAttribute("question") Question question, @PathVariable int quizId)
    {
        questionDao.addQuestion(question);
        return "redirect:/quiz/" + quizId + "/details";
    }

    @GetMapping("/quiz/{quizId}/{questionId}/edit")
    public String updateQuestion(Model model, @PathVariable int quizId, @PathVariable int questionId)
    {
        Question question = questionDao.getQuestionById(questionId);
        List<Quiz> quizzes = quizDao.getAllQuizzes();
        Quiz quiz = quizDao.getQuizById(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("question", question);
        model.addAttribute("action", "edit");

        return "details/add-edit-question";
    }

    @PostMapping("/quiz/{quizId}/{questionId}/edit")
    public String updateQuestion(@ModelAttribute("question") Question question, @PathVariable int quizId, @PathVariable int questionId)
    {
        question.setQuestionId(questionId);
        questionDao.editQuestion(question);

        return "redirect:/quiz/" + quizId + "/details";
    }
}
