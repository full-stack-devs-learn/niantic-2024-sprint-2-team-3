package com.nianti.services;

import com.nianti.models.Question;
import com.nianti.models.Quiz;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDao()
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


    public List<Question> getQuestionByQuizId(int quizId)
    {
        List<Question> questions = new ArrayList<>();
        String sql= """
                SELECT question_id
                       ,quiz_id
                       ,question_number
                       ,quiz_text
                FROM question
                WHERE quiz_id = ?;
               
               """;
        var row = jdbcTemplate.queryForRowSet(sql, quizId);

        while (row.next())
        {
           int questionId = row.getInt("question_id");
            int questionNumber = row.getInt("question_number");
            String quizText = row.getString("quiz_text");

            Question question= new Question(questionId, quizId,questionNumber,quizText);
            questions.add(question);
        }

       return questions;
    }



    public int getQuestionCount(int quizId)
    {
        String sql = """
                SELECT COUNT(*) AS question_count
                FROM question
                WHERE quiz_id = ?;
                """;

        var row = jdbcTemplate.queryForRowSet(sql, quizId);

        if (row.next())
        {
            return row.getInt("question_count");
        }

        return 0;
    }
}
