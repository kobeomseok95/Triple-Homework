package com.triple.homework.user.application.port.in.response;

import java.util.UUID;

public class UserPointResponseDtoBuilder {

    public static UserPointResponseDto build() {
        return UserPointResponseDto.builder()
                .userId(UUID.randomUUID().toString())
                .point(10L)
                .build();
    }
}
