package com.triple.homework.review.adapter.in.request;

import java.util.List;

public class ReviewEventRequestBuilder {

    public static ReviewEventRequest nullFieldBuild() {
        return ReviewEventRequest.builder().build();
    }

    public static ReviewEventRequest buildInvalidTypeAction() {
        return ReviewEventRequest.builder()
                .type("12341")
                .action("12345")
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .content("안녕하세요")
                .attachedPhotoIds(List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"))
                .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();
    }

    public static ReviewEventRequest buildAdd() {
        return ReviewEventRequest.builder()
                .type("REVIEW")
                .action("ADD")
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .content("안녕하세요")
                .attachedPhotoIds(List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"))
                .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();
    }

    public static ReviewEventRequest buildModify() {
        return ReviewEventRequest.builder()
                .type("REVIEW")
                .action("MOD")
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .content("안녕하세요")
                .attachedPhotoIds(List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"))
                .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();
    }

    public static ReviewEventRequest buildModifyHaveNotContentAttachedPhotoIds() {
        return ReviewEventRequest.builder()
                .type("REVIEW")
                .action("MOD")
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();
    }

    public static ReviewEventRequest buildDelete() {
        return ReviewEventRequest.builder()
                .type("REVIEW")
                .action("DELETE")
                .reviewId("240a0658-dc5f-4878-9381-ebb7b2667772")
                .content("안녕하세요")
                .attachedPhotoIds(List.of("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"))
                .userId("3ede0ef2-92b7-4817-a5f3-0c575361f745")
                .placeId("2e4baf1c-5acb-4efb-a1af-eddada31b00f")
                .build();
    }
}
