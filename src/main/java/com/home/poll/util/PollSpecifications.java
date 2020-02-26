package com.home.poll.util;

import com.home.poll.model.Poll;
import liquibase.pro.packaged.cb;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PollSpecifications implements Specification<Poll> {
    private List<SearchCriteria> list;

    public PollSpecifications() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Poll> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                if (!criteria.getDType().equals("date")) {
                    predicates.add(builder.greaterThan(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else {
                    predicates.add(builder.greaterThan(
                            root.get(criteria.getKey()), new Date(Integer.parseInt(criteria.getValue().toString()))));
                }
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                if (!criteria.getDType().equals("date")) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), new Date(Integer.parseInt(criteria.getValue().toString()))));
                }
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                if (!criteria.getDType().equals("date")) {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), new Date(Integer.parseInt(criteria.getValue().toString()))));
                }
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperation().equals(SearchOperation.IN)) {
                predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
