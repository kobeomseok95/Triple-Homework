package com.triple.homework.user.application.port.in.response;

import com.triple.homework.user.domain.User;

public class UserPointResponseDto {

    private String userId;
    private Long point;

    private UserPointResponseDto(String userId, Long point) {
        this.userId = userId;
        this.point = point;
    }

    private UserPointResponseDto() {
    }

    public static UserPointResponseDto from(User user) {
        return UserPointResponseDto.builder()
                .userId(user.getId())
                .point(user.getUserPoints())
                .build();
    }

    public static UserPointResponseDtoBuilder builder() {
        return new UserPointResponseDtoBuilder();
    }

    public String getUserId() {
        return this.userId;
    }

    public Long getPoint() {
        return this.point;
    }

    public static class UserPointResponseDtoBuilder {
        private String userId;
        private Long point;

        UserPointResponseDtoBuilder() {
        }

        public UserPointResponseDtoBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserPointResponseDtoBuilder point(Long point) {
            this.point = point;
            return this;
        }

        public UserPointResponseDto build() {
            return new UserPointResponseDto(userId, point);
        }

        public String toString() {
            return "UserPointResponseDto.UserPointResponseDtoBuilder(userId=" + this.userId + ", point=" + this.point + ")";
        }
    }
}
