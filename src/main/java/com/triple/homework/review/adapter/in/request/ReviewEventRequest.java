package com.triple.homework.review.adapter.in.request;

import com.triple.homework.common.validation.EnumValid;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Builder.Default
    private List<String> attachedPhotoIds = new ArrayList<>();

    @NotBlank(message = "유저 ID를 입력해주세요.")
    private String userId;

    @NotBlank(message = "장소 ID를 입력해주세요.")
    private String placeId;

    public ReviewEventRequestDto toRequestDto() {
        return ReviewEventRequestDto.builder()
                .reviewId(reviewId)
                .content(content)
                .attachedPhotoIds(attachedPhotoIds)
                .userId(userId)
                .placeId(placeId)
                .build();
    }
}
