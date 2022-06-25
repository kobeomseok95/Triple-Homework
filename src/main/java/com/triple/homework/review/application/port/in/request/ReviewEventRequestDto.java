package com.triple.homework.review.application.port.in.request;

import com.triple.homework.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEventRequestDto {

    private String reviewId;
    private String content;
    @Builder.Default
    private List<String> attachedPhotoIds = new ArrayList<>();
    private String userId;
    private String placeId;

    public Review toReview() {
        return Review.builder()
                .id(reviewId)
                .content(content)
                .userId(userId)
                .placeId(placeId)
                .isDeleted(false)
                .build();
    }
}
