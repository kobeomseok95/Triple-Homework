package com.triple.homework.review.application.port.in.response;

import com.triple.homework.user.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserPointHistoryResponseDto {

    private User user;
    private Long changedPoint;

    public static UserPointHistoryResponseDto of(User user, Long point) {
        return UserPointHistoryResponseDto.builder()
                .user(user)
                .changedPoint(point)
                .build();
    }
}
