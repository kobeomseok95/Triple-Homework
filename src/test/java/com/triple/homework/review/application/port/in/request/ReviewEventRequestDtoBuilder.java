package com.triple.homework.review.application.port.in.request;

import java.util.List;
import java.util.UUID;

public class ReviewEventRequestDtoBuilder {

    public static ReviewEventRequestDto build() {
        return ReviewEventRequestDto.builder()
                .reviewId(UUID.randomUUID().toString())
                .userId(UUID.randomUUID().toString())
                .placeId(UUID.randomUUID().toString())
                .content("테스트 컨텐츠")
                .attachedPhotoIds(List.of(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                ))
                .build();
    }
}
