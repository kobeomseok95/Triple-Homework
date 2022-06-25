package com.triple.homework.fixture;

import com.triple.homework.user.domain.User;

import java.util.UUID;

public class UserFixture {

    public static User user() {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .pointScore(0L)
                .build();
    }
}
