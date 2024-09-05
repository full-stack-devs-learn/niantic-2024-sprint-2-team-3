package com.nianti.controllers;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import com.nianti.services.AnswerDao;
import com.nianti.services.QuestionDao;
import com.nianti.services.QuizDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizPageApiController
{
    QuizDao quizDao = new QuizDao();
    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDao = new AnswerDao();

    @GetMapping("/api/quiz/{quizId}/{questionId}")
    public List<Question> getAllQuestions(@PathVariable int quizId, @PathVariable int questionId)
    {
        List<Question> questions;
        questions = questionDao.getQuestionByQuizId(quizId);

        return questions;
    }

    @GetMapping("/api/quiz/{questionId}/answer")
    public List<Answer> getAllAnswers(@PathVariable int quizId, @PathVariable int questionId)
    {
        List<Answer> answers;
        answers = answerDao.getAnswersByQuestionId(questionId);

        return answers;
    }


    @GetMapping("api/quiz/{quizId}/questionCount")
    public int getQuestionCount(@PathVariable int quizId)
    {

        int count = questionDao.getQuestionCount(quizId);

        return count;
    }


}
