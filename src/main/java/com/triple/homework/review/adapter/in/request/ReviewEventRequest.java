package com.triple.homework.review.adapter.in.request;

import com.triple.homework.common.validation.EnumValid;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class ReviewEventRequest {

    @EnumValid(
            message = "이벤트 타입을 입력해주세요.",
            enumClass = EventType.class
    )
    private String type;

    @EnumValid(
            message = "이벤트 유형을 입력해주세요. 생성은 \"ADD\", 수정은 \"MOD\", 삭제는 \"DELETE\" 입니다.",
            enumClass = ActionType.class
    )
    private String action;

    @NotBlank(message = "리뷰 ID를 입력해주세요.")
    private String reviewId;

    private String content;

    private List<String> attachedPhotoIds = new ArrayList<>();

    @NotBlank(message = "유저 ID를 입력해주세요.")
    private String userId;

    @NotBlank(message = "장소 ID를 입력해주세요.")
    private String placeId;

    public ReviewEventRequest(@EnumValid(
            message = "이벤트 타입을 입력해주세요.",
            enumClass = EventType.class
    ) String type, @EnumValid(
            message = "이벤트 유형을 입력해주세요. 생성은 \"ADD\", 수정은 \"MOD\", 삭제는 \"DELETE\" 입니다.",
            enumClass = ActionType.class
    ) String action, @NotBlank(message = "리뷰 ID를 입력해주세요.") String reviewId, String content, List<String> attachedPhotoIds, @NotBlank(message = "유저 ID를 입력해주세요.") String userId, @NotBlank(message = "장소 ID를 입력해주세요.") String placeId) {
        this.type = type;
        this.action = action;
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }

    public ReviewEventRequest() {
    }

    private static List<String> $default$attachedPhotoIds() {
        return new ArrayList<>();
    }

    public static ReviewEventRequestBuilder builder() {
        return new ReviewEventRequestBuilder();
    }

    public ReviewEventRequestDto toRequestDto() {
        return ReviewEventRequestDto.builder()
                .reviewId(reviewId)
                .content(content)
                .attachedPhotoIds(attachedPhotoIds)
                .userId(userId)
                .placeId(placeId)
                .build();
    }

    public @EnumValid(
            message = "이벤트 타입을 입력해주세요.",
            enumClass = EventType.class
    ) String getType() {
        return this.type;
    }

    public @EnumValid(
            message = "이벤트 유형을 입력해주세요. 생성은 \"ADD\", 수정은 \"MOD\", 삭제는 \"DELETE\" 입니다.",
            enumClass = ActionType.class
    ) String getAction() {
        return this.action;
    }

    public @NotBlank(message = "리뷰 ID를 입력해주세요.") String getReviewId() {
        return this.reviewId;
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getAttachedPhotoIds() {
        return this.attachedPhotoIds;
    }

    public @NotBlank(message = "유저 ID를 입력해주세요.") String getUserId() {
        return this.userId;
    }

    public @NotBlank(message = "장소 ID를 입력해주세요.") String getPlaceId() {
        return this.placeId;
    }

    public void setType(@EnumValid(
            message = "이벤트 타입을 입력해주세요.",
            enumClass = EventType.class
    ) String type) {
        this.type = type;
    }

    public void setAction(@EnumValid(
            message = "이벤트 유형을 입력해주세요. 생성은 \"ADD\", 수정은 \"MOD\", 삭제는 \"DELETE\" 입니다.",
            enumClass = ActionType.class
    ) String action) {
        this.action = action;
    }

    public void setReviewId(@NotBlank(message = "리뷰 ID를 입력해주세요.") String reviewId) {
        this.reviewId = reviewId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAttachedPhotoIds(List<String> attachedPhotoIds) {
        this.attachedPhotoIds = attachedPhotoIds;
    }

    public void setUserId(@NotBlank(message = "유저 ID를 입력해주세요.") String userId) {
        this.userId = userId;
    }

    public void setPlaceId(@NotBlank(message = "장소 ID를 입력해주세요.") String placeId) {
        this.placeId = placeId;
    }

    public static class ReviewEventRequestBuilder {
        private @EnumValid(
                message = "이벤트 타입을 입력해주세요.",
                enumClass = EventType.class
        ) String type;
        private @EnumValid(
                message = "이벤트 유형을 입력해주세요. 생성은 \"ADD\", 수정은 \"MOD\", 삭제는 \"DELETE\" 입니다.",
                enumClass = ActionType.class
        ) String action;
        private @NotBlank(message = "리뷰 ID를 입력해주세요.") String reviewId;
        private String content;
        private List<String> attachedPhotoIds$value;
        private boolean attachedPhotoIds$set;
        private @NotBlank(message = "유저 ID를 입력해주세요.") String userId;
        private @NotBlank(message = "장소 ID를 입력해주세요.") String placeId;

        ReviewEventRequestBuilder() {
        }

        public ReviewEventRequestBuilder type(@EnumValid(
                message = "이벤트 타입을 입력해주세요.",
                enumClass = EventType.class
        ) String type) {
            this.type = type;
            return this;
        }

        public ReviewEventRequestBuilder action(@EnumValid(
                message = "이벤트 유형을 입력해주세요. 생성은 \"ADD\", 수정은 \"MOD\", 삭제는 \"DELETE\" 입니다.",
                enumClass = ActionType.class
        ) String action) {
            this.action = action;
            return this;
        }

        public ReviewEventRequestBuilder reviewId(@NotBlank(message = "리뷰 ID를 입력해주세요.") String reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public ReviewEventRequestBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ReviewEventRequestBuilder attachedPhotoIds(List<String> attachedPhotoIds) {
            this.attachedPhotoIds$value = attachedPhotoIds;
            this.attachedPhotoIds$set = true;
            return this;
        }

        public ReviewEventRequestBuilder userId(@NotBlank(message = "유저 ID를 입력해주세요.") String userId) {
            this.userId = userId;
            return this;
        }

        public ReviewEventRequestBuilder placeId(@NotBlank(message = "장소 ID를 입력해주세요.") String placeId) {
            this.placeId = placeId;
            return this;
        }

        public ReviewEventRequest build() {
            List<String> attachedPhotoIds$value = this.attachedPhotoIds$value;
            if (!this.attachedPhotoIds$set) {
                attachedPhotoIds$value = ReviewEventRequest.$default$attachedPhotoIds();
            }
            return new ReviewEventRequest(type, action, reviewId, content, attachedPhotoIds$value, userId, placeId);
        }

        public String toString() {
            return "ReviewEventRequest.ReviewEventRequestBuilder(type=" + this.type + ", action=" + this.action + ", reviewId=" + this.reviewId + ", content=" + this.content + ", attachedPhotoIds$value=" + this.attachedPhotoIds$value + ", userId=" + this.userId + ", placeId=" + this.placeId + ")";
        }
    }
}
