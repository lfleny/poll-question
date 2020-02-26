package com.home.poll.service;

import com.home.poll.model.Poll;

import java.util.List;

public interface PollService {

    void create(Poll poll);

    List<Poll> readAll();

    Poll read(int id);

    boolean update(Poll poll, int id);

    boolean delete(int id);
}
