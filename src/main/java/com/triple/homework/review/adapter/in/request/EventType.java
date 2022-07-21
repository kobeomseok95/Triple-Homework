package com.triple.homework.review.adapter.in.request;

public enum EventType {

    REVIEW("REVIEW"),

    ;

    private final String value;

    private EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
