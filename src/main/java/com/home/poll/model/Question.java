package com.home.poll.model;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "poll_id")
    private Long pollId;

    @NotEmpty(message = "Empty question text field")
    @Column(name = "text")
    private String text;

    @NotNull(message = "Empty question order field")
    @Column(name = "question_order")
    private Integer order;

    public String getText() {
        return text;
    }

    public Long getPollId() {
        return pollId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
