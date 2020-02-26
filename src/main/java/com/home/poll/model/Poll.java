package com.home.poll.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "polls")
@SecondaryTable(name = "questions")
public class Poll {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private Integer status;

//    @ManyToOne(fetch = FetchType.LAZY, mappedBy = "questions")
//    private List<Question> questions;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "poll_id")
    private List<Question> questions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setStatus(Integer newStatus) {
        status = newStatus;
    }

    public void setEndDate(Date newDate) {
        endDate = newDate;
    }

    public void setStartDate(Date newDate) {
        startDate = newDate;
    }
}
