package com.home.poll.controller;

import com.home.poll.model.Question;
import com.home.poll.repository.QuestionRepository;
import com.home.poll.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(value = "/questions")
    public Question createQuestion(@RequestBody @Valid Question question) {
        return questionRepository.save(question);
    }
}
