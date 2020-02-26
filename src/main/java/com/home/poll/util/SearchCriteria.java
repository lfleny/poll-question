package com.home.poll.util;

import java.util.Date;

public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;
    private String dType;

    public SearchCriteria(String key, Object value, SearchOperation operation, String type) {
        this.key = key;
        this.value = value;
        this.operation = operation;
        this.dType = type;
    }

    public String getKey() {
        return key;
    }

    public String getDType() {
        return dType;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", operation=" + operation +
                '}';
    }
}