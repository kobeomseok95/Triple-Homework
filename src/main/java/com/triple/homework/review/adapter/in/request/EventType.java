package com.triple.homework.review.adapter.in.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {

    REVIEW("REVIEW"),
    ;

    private String value;
}
