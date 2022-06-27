package com.triple.homework.review.application.port.in.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserPointHistoryResponse {

    private String userId;
    private Long changedPoint;
}
