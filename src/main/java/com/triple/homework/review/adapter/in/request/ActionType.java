package com.triple.homework.review.adapter.in.request;

public enum ActionType {

    ADD("ADD"),
    MOD("MOD"),
    DELETE("DELETE"),
    ;

    private final String value;

    private ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
