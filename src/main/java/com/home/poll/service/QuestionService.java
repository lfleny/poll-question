package com.home.poll.service;

import com.home.poll.model.Question;
import java.util.List;

public interface QuestionService {

    void create(Question question);

    List<Question> readAll();

    boolean update(Question question, int id);

    boolean delete(int id);
}
