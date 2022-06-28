package com.triple.homework.user.application.port.in.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserPointResponseDto {

    private String userId;
    private Long point;
}
