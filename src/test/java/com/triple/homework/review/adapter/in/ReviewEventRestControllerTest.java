package com.triple.homework.review.adapter.in;

import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import com.triple.homework.review.adapter.in.request.ReviewEventRequestBuilder;
import com.triple.homework.support.RestControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewEventRestControllerTest extends RestControllerTestSupport {

    @DisplayName("이벤트 받기 - 유효하지 않은 입력값 테스트")
    @ParameterizedTest
    @MethodSource("invalidTypeActions")
    void review_events_test_fail_invalid_type_actions(ReviewEventRequest reviewEventRequest, int errorCounts) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/events")
                .content(objectMapper.writeValueAsString(reviewEventRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(errorCounts)))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    private static Stream<Arguments> invalidTypeActions() {
        return Stream.of(
                Arguments.of(ReviewEventRequestBuilder.nullFieldBuild(), 5)
        );
    }
}