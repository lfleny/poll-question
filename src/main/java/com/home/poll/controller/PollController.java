package com.home.poll.controller;

import com.home.poll.exception.ResourceNotFoundException;
import com.home.poll.exception.WrongRequestParametersException;
import com.home.poll.model.Poll;
import com.home.poll.repository.PollRepository;
import com.home.poll.service.PollService;
import com.home.poll.util.PollSpecifications;
import com.home.poll.util.SearchCriteria;
import com.home.poll.util.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PollController {

    private final PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping(value = "/polls")
    public Poll createPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }

    @GetMapping(value = "/polls")
    public List<Poll> getQuestions(@RequestParam("orderBy") String orderBy,
                                   @RequestParam("direction") String direction,
                                   @RequestParam(name = "name", required=false) String name,
                                   @RequestParam(name = "status", required=false) Integer status,
                                   @RequestParam(name = "fromStartDate", required=false) Integer fromStartDate,
                                   @RequestParam(name = "toStartDate", required=false) Integer toStartDate,
                                   @RequestParam(name = "fromEndDate", required=false) Integer fromEndDate,
                                   @RequestParam(name = "toEndDate", required=false) Integer toEndDate) {
        List<Poll> result;

        if ((!direction.toLowerCase().equals("desc") && !direction.toLowerCase().equals("asc")) &&
                (!orderBy.toLowerCase().equals("startDate") && !orderBy.toLowerCase().equals("name"))) {
            throw new WrongRequestParametersException("Wrong sort parameters");
        }

        try {
            PollSpecifications pollSpec = new PollSpecifications();
            if (name != null) {
                pollSpec.add(new SearchCriteria("name", name, SearchOperation.MATCH, "varchar"));
            }
            if (status != null) {
                pollSpec.add(new SearchCriteria("status", status, SearchOperation.EQUAL, "integer"));
            }
            if (fromStartDate != null) {
                pollSpec.add(new SearchCriteria("startDate", fromStartDate, SearchOperation.GREATER_THAN_EQUAL, "date"));
            }
            if (toStartDate != null) {
                pollSpec.add(new SearchCriteria("startDate", toStartDate, SearchOperation.LESS_THAN_EQUAL, "date"));
            }
            if (fromEndDate != null) {
                pollSpec.add(new SearchCriteria("endDate", fromEndDate, SearchOperation.GREATER_THAN_EQUAL, "date"));
            }
            if (toEndDate != null) {
                pollSpec.add(new SearchCriteria("endDate", toEndDate, SearchOperation.LESS_THAN_EQUAL, "date"));
            }
            result = pollRepository.findAll(pollSpec,
                    direction.equals("asc") ? Sort.by(Sort.Direction.ASC, orderBy) : Sort.by(Sort.Direction.DESC, orderBy));
        } catch (Exception e) {
            throw new WrongRequestParametersException("Wrong filter parameters");
        }
        return result;
    }

    @PutMapping("/polls/{pollId}")
    public Poll updatePull(@PathVariable Long pollId,
                                   @Valid @RequestBody Poll pollRequest) {
        return pollRepository.findById(pollId)
                .map(poll -> {
                    poll.setName(pollRequest.getName());
                    poll.setStatus(pollRequest.getStatus());
                    poll.setEndDate(pollRequest.getEndDate());
                    poll.setStartDate(pollRequest.getStartDate());
                    return pollRepository.save(poll);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + pollId));
    }

    @DeleteMapping("/polls/{pollsId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long pollsId) {
        return pollRepository.findById(pollsId)
                .map(question -> {
                    pollRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Poll not found with id " + pollsId));
    }
}
