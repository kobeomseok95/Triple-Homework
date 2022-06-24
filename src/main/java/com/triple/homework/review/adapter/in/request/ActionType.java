package com.triple.homework.review.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {

    ADD("ADD"),
    MOD("MOD"),
    DELETE("DELETE"),
    ;

    private String value;
}
