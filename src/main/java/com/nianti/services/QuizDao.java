package com.nianti.services;

import com.nianti.models.Quiz;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuizDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuizDao()
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

    public List<Quiz> getAllQuizzes()
    {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = """
            SELECT quiz_id
                , quiz_title
                , is_live
            FROM quiz;
        """;
        var row = jdbcTemplate.queryForRowSet(sql);
        while (row.next())
        {
            var quiz = mapRowToQuiz(row);
            quizzes.add(quiz);
        }

        return quizzes;
    }

    public List<Quiz> sortLiveQuiz()
    {
        List<Quiz> sortedQuizzes = new ArrayList<>();

        String sql = """
                SELECT quiz_id
                    , quiz_title
                    , is_live
                FROM quiz
                ORDER BY is_live DESC;
                """;

        var row = jdbcTemplate.queryForRowSet(sql);

        while (row.next())
        {
            var quiz = mapRowToQuiz(row);
            sortedQuizzes.add(quiz);
        }

        return sortedQuizzes;
    }

    public Quiz getQuizById(int id)
    {
        Quiz quiz = null;
        String sql = """
            SELECT quiz_id
                , quiz_title
                , is_live
            FROM quiz
            WHERE quiz_id = ?;
        """;

        var row = jdbcTemplate.queryForRowSet(sql, id);

        if (row.next())
        {
            quiz = mapRowToQuiz(row);
        }

        return quiz;
    }

    private Quiz mapRowToQuiz(SqlRowSet row)
    {
        int id = row.getInt("quiz_id");
        String title = row.getString("quiz_title");
        boolean isLive = row.getBoolean("is_live");

        return new Quiz(id, title, isLive);
    }


    public void addQuiz(Quiz quiz)
    {
        String sql= """
                INSERT INTO quiz (quiz_title, is_live)
                VALUES (?, ?);
                """;
        jdbcTemplate.update(sql
                ,quiz.getTitle()
                ,quiz.getIsLive());

    }

    public void editQuiz(Quiz quiz)
    {
        String sql= """
                UPDATE quiz
                SET quiz_title = ?
                    , is_live = ?
                WHERE quiz_id = ?;
                """;

        jdbcTemplate.update(sql
            , quiz.getTitle()
            , quiz.getIsLive()
            , quiz.getQuizId());
    }
}
