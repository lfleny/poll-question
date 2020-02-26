package com.home.poll.repository;

import com.home.poll.model.Poll;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long>, JpaSpecificationExecutor<Poll> {
}
