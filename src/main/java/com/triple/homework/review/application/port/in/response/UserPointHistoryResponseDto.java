package com.triple.homework.review.application.port.in.response;

import com.triple.homework.history.domain.PointHistory;
import com.triple.homework.user.domain.User;

import java.util.UUID;

public class UserPointHistoryResponseDto {

    private User user;
    private Long changedPoint;

    private UserPointHistoryResponseDto(User user, Long changedPoint) {
        this.user = user;
        this.changedPoint = changedPoint;
    }

    private UserPointHistoryResponseDto() {
    }

    public static UserPointHistoryResponseDto of(User user, Long point) {
        return UserPointHistoryResponseDto.builder()
                .user(user)
                .changedPoint(point)
                .build();
    }

    public static UserPointHistoryResponseDtoBuilder builder() {
        return new UserPointHistoryResponseDtoBuilder();
    }

    public PointHistory toPointHistory() {
        return PointHistory.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .point(changedPoint)
                .build();
    }

    public User getUser() {
        return this.user;
    }

    public Long getChangedPoint() {
        return this.changedPoint;
    }

    public static class UserPointHistoryResponseDtoBuilder {
        private User user;
        private Long changedPoint;

        UserPointHistoryResponseDtoBuilder() {
        }

        public UserPointHistoryResponseDtoBuilder user(User user) {
            this.user = user;
            return this;
        }

        public UserPointHistoryResponseDtoBuilder changedPoint(Long changedPoint) {
            this.changedPoint = changedPoint;
            return this;
        }

        public UserPointHistoryResponseDto build() {
            return new UserPointHistoryResponseDto(user, changedPoint);
        }

        public String toString() {
            return "UserPointHistoryResponseDto.UserPointHistoryResponseDtoBuilder(user=" + this.user + ", changedPoint=" + this.changedPoint + ")";
        }
    }
}
