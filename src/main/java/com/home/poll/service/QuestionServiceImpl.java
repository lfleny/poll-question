package com.home.poll.service;

import com.home.poll.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Override
    public void create(Question question) {}

    @Override
    public List<Question> readAll() {
        return null;
    }

    @Override
    public boolean update(Question question, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}
