package com.triple.homework.user.adapter.in.response;

import com.triple.homework.user.application.port.in.response.UserPointResponseDto;

public class UserPointResponse {

    private String userId;
    private Long point;

    private UserPointResponse(String userId, Long point) {
        this.userId = userId;
        this.point = point;
    }

    private UserPointResponse() {
    }

    public static UserPointResponse from(UserPointResponseDto responseDto) {
        return UserPointResponse.builder()
                .userId(responseDto.getUserId())
                .point(responseDto.getPoint())
                .build();
    }

    public static UserPointResponseBuilder builder() {
        return new UserPointResponseBuilder();
    }

    public String getUserId() {
        return this.userId;
    }

    public Long getPoint() {
        return this.point;
    }

    public static class UserPointResponseBuilder {
        private String userId;
        private Long point;

        UserPointResponseBuilder() {
        }

        public UserPointResponseBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserPointResponseBuilder point(Long point) {
            this.point = point;
            return this;
        }

        public UserPointResponse build() {
            return new UserPointResponse(userId, point);
        }

        public String toString() {
            return "UserPointResponse.UserPointResponseBuilder(userId=" + this.userId + ", point=" + this.point + ")";
        }
    }
}
