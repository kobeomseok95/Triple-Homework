package com.triple.homework.user.adapter.in.response;

import com.triple.homework.user.application.port.in.response.UserPointResponseDto;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserPointResponse {

    private String userId;
    private Long point;

    public static UserPointResponse from(UserPointResponseDto responseDto) {
        return UserPointResponse.builder()
                .userId(responseDto.getUserId())
                .point(responseDto.getPoint())
                .build();
    }
}
