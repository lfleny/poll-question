package com.home.poll.service;

import com.home.poll.model.Poll;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    @Override
    public void create(Poll poll) {}

    @Override
    public List<Poll> readAll() {
        return null;
    }

    @Override
    public Poll read(int id) {
        return null;
    }

    @Override
    public boolean update(Poll poll, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
