package com.nianti.controllers;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import com.nianti.models.Quiz;
import com.nianti.services.AnswerDao;
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
    AnswerDao answerDao = new AnswerDao();

    @GetMapping("/quiz/{quizId}/details")
    public String getQuizDetails(Model model, @PathVariable int quizId)
    {
        Quiz quiz = quizDao.getQuizById(quizId);
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        model.addAttribute("title", String.format("Details: %s", quiz.getTitle()));

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
        model.addAttribute("title", "Add Question");

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
        model.addAttribute("title", "Edit Question");

        return "details/add-edit-question";
    }

    @PostMapping("/quiz/{quizId}/{questionId}/edit")
    public String updateQuestion(@ModelAttribute("question") Question question, @PathVariable int quizId, @PathVariable int questionId)
    {
        question.setQuestionId(questionId);
        questionDao.editQuestion(question);

        return "redirect:/quiz/" + quizId + "/details";
    }

    @GetMapping("/quiz/{quizId}/{questionId}/details")
    public String getQuestionDetails(Model model, @PathVariable int quizId, @PathVariable int questionId)
    {
        Quiz quiz = quizDao.getQuizById(quizId);
        Question question = questionDao.getQuestionById(questionId);
        List<Answer> answers = answerDao.getAnswersByQuestionId(questionId);

        model.addAttribute("quiz", quiz);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        model.addAttribute("title", String.format("Question Details: %s", quiz.getTitle()));

        return "details/question-details";
    }

    @GetMapping("/quiz/{quizId}/{questionId}/answer/add")
    public String addAnswer(Model model, @PathVariable int quizId, @PathVariable int questionId)
    {
        Question question = questionDao.getQuestionById(questionId);
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);

        model.addAttribute("answer", new Answer());
        model.addAttribute("question", question);
        model.addAttribute("questions", questions);
        model.addAttribute("quizId", quizId);
        model.addAttribute("action", "add");
        model.addAttribute("title", "Add Answer");

        return "details/add-edit-answer";
    }

    @PostMapping("/quiz/{quizId}/{questionId}/answer/add")
    public String addQuestion(@ModelAttribute("answer") Answer answer, @PathVariable int quizId, @PathVariable int questionId)
    {
        answerDao.addAnswer(answer);

        return "redirect:/quiz/" + quizId + "/" + questionId + "/details";
    }

    @GetMapping("/quiz/{quizId}/{questionId}/{answerId}/edit")
    public String updateAnswer(Model model, @PathVariable int quizId, @PathVariable int questionId, @PathVariable int answerId)
    {
        Answer answer = answerDao.getAnswerById(answerId);
        Question question = questionDao.getQuestionById(questionId);
        List<Question> questions = questionDao.getQuestionByQuizId(quizId);

        model.addAttribute("answer", answer);
        model.addAttribute("question", question);
        model.addAttribute("questions", questions);
        model.addAttribute("quizId", quizId);
        model.addAttribute("action", "edit");
        model.addAttribute("title", "Edit Answer");

        return "details/add-edit-answer";
    }

    @PostMapping ("/quiz/{quizId}/{questionId}/{answerId}/edit")
    public String updateAnswer(@ModelAttribute("answer") Answer answer, @PathVariable int quizId, @PathVariable int questionId, @PathVariable int answerId)
    {
        answer.setAnswerId(answerId);
        answerDao.editAnswer(answer);

        return "redirect:/quiz/" + quizId + "/" + questionId + "/details";
    }
}
