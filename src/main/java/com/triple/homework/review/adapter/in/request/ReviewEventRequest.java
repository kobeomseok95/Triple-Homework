package com.triple.homework.review.adapter.in.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEventRequest {

    @NotNull(message = "이벤트 타입을 입력해주세요.")
    private EventType type;
    @NotNull(message = "이벤트 유형을 입력해주세요. 생성은 \"ADD\", 수정은 \"MOD\", 삭제는 \"DELETE\" 입니다.")
    private ActionType action;
    @NotNull(message = "리뷰 ID를 입력해주세요.")
    private String reviewId;
    private String content;
    @Builder.Default
    private List<String> attachedPhotoIds = new ArrayList<>();
    @NotNull(message = "유저 ID를 입력해주세요.")
    private String userId;
    @NotNull(message = "장소 ID를 입력해주세요.")
    private String placeId;
}
