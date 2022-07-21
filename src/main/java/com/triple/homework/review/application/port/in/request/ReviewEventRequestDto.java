package com.triple.homework.review.application.port.in.request;

import com.triple.homework.review.domain.Review;
import com.triple.homework.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ReviewEventRequestDto {

    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds = new ArrayList<>();
    private String userId;
    private String placeId;

    public ReviewEventRequestDto(String reviewId, String content, List<String> attachedPhotoIds, String userId, String placeId) {
        this.reviewId = reviewId;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.userId = userId;
        this.placeId = placeId;
    }

    public ReviewEventRequestDto() {
    }

    private static List<String> $default$attachedPhotoIds() {
        return new ArrayList<>();
    }

    public static ReviewEventRequestDtoBuilder builder() {
        return new ReviewEventRequestDtoBuilder();
    }

    public Review toReview(User user, Long points) {
        return Review.builder()
                .id(reviewId)
                .user(user)
                .content(content)
                .placeId(placeId)
                .reviewPoints(points)
                .build();
    }

    public String getReviewId() {
        return this.reviewId;
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getAttachedPhotoIds() {
        return this.attachedPhotoIds;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public static class ReviewEventRequestDtoBuilder {
        private String reviewId;
        private String content;
        private List<String> attachedPhotoIds$value;
        private boolean attachedPhotoIds$set;
        private String userId;
        private String placeId;

        ReviewEventRequestDtoBuilder() {
        }

        public ReviewEventRequestDtoBuilder reviewId(String reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public ReviewEventRequestDtoBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ReviewEventRequestDtoBuilder attachedPhotoIds(List<String> attachedPhotoIds) {
            this.attachedPhotoIds$value = attachedPhotoIds;
            this.attachedPhotoIds$set = true;
            return this;
        }

        public ReviewEventRequestDtoBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public ReviewEventRequestDtoBuilder placeId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public ReviewEventRequestDto build() {
            List<String> attachedPhotoIds$value = this.attachedPhotoIds$value;
            if (!this.attachedPhotoIds$set) {
                attachedPhotoIds$value = ReviewEventRequestDto.$default$attachedPhotoIds();
            }
            return new ReviewEventRequestDto(reviewId, content, attachedPhotoIds$value, userId, placeId);
        }

        public String toString() {
            return "ReviewEventRequestDto.ReviewEventRequestDtoBuilder(reviewId=" + this.reviewId + ", content=" + this.content + ", attachedPhotoIds$value=" + this.attachedPhotoIds$value + ", userId=" + this.userId + ", placeId=" + this.placeId + ")";
        }
    }
}
