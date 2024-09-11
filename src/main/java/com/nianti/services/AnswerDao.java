package com.nianti.services;

import com.nianti.models.Answer;
import com.nianti.models.Question;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnswerDao()
    {
        String databaseUrl = "jdbc:mysql://localhost:3306/trivio";
        String userName = "root";
        String password = "P@ssw0rd";
        DataSource dataSource = new BasicDataSource(){{
            setUrl(databaseUrl);
            setUsername(userName);
            setPassword(password);
        }};

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Answer> getAnswersByQuestionId(int questionId)
    {
        List<Answer> answers = new ArrayList<>();
        String sql = """
                SELECT  answer_id
                    , answer_text
                    , is_correct
               FROM answer
               WHERE question_id = ?;
               """;

        var row = jdbcTemplate.queryForRowSet(sql, questionId);

        while (row.next())
        {
            int answerId = row.getInt("answer_id");
            String answerText = row.getString("answer_text");
            Boolean isCorrect = row.getBoolean("is_correct");

            Answer answer= new Answer(answerId,questionId,answerText, isCorrect);
            answers.add(answer);
        }

        return answers;
    }

    public Answer getAnswerById(int answerId)
    {
        String sql = """
                SELECT question_id
                    , answer_text
                    , is_correct
               FROM answer
               WHERE answer_id = ?;
               """;

        var row = jdbcTemplate.queryForRowSet(sql, answerId);

        if (row.next())
        {
            int questionId = row.getInt("question_id");
            String answerText = row.getString("answer_text");
            Boolean isCorrect = row.getBoolean("is_correct");

            Answer answer = new Answer(answerId, questionId, answerText, isCorrect);
            return answer;
        }

        return null;
    }

    public void addAnswer(Answer answer)
    {
        String sql = """
                INSERT INTO answer (question_id, answer_text, is_correct)
                VALUES (?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , answer.getQuestionId()
                , answer.getAnswerText()
                , answer.getIsCorrect());
    }

    public void editAnswer(Answer answer)
    {
        String sql = """
                UPDATE answer
                SET question_id = ?
                    , answer_text = ?
                    , is_correct = ?
                WHERE answer_id = ?
                """;

        jdbcTemplate.update(sql
                , answer.getQuestionId()
                , answer.getAnswerText()
                , answer.getIsCorrect()
                , answer.getAnswerId());
    }
}
