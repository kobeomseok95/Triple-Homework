package com.triple.homework.user.application.port.in.response;

import com.triple.homework.user.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserPointResponseDto {

    private String userId;
    private Long point;

    public static UserPointResponseDto from(User user) {
        return UserPointResponseDto.builder()
                .userId(user.getId())
                .point(user.getUserPoints())
                .build();
    }
}
