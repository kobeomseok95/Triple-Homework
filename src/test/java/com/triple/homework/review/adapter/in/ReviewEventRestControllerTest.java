package com.triple.homework.review.adapter.in;

import com.triple.homework.common.exception.ClientErrorCode;
import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import com.triple.homework.review.adapter.in.request.ReviewEventRequestBuilder;
import com.triple.homework.support.RestControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewEventRestControllerTest extends RestControllerTestSupport {

    @DisplayName("이벤트 받기 - 유효하지 않은 입력값이 주어진 경우")
    @Test
    void review_events_fail_invalid_inputs() throws Exception {

        ReviewEventRequest request = ReviewEventRequestBuilder.nullFieldBuild();

        mockMvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(5)))
                .andExpect(jsonPath("$.code", is(ClientErrorCode.INVALID_REQUEST.getCode())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    @DisplayName("이벤트 받기 - 유효하지 않은 type, action인 경우")
    @Test
    void review_events_fail_invalid_type_actions() throws Exception {

        mockMvc.perform(post("/events")
                .content("{" +
                                "\"type\": \"123412\"," +
                                "\"action\": \"1234123\"," +
                                "\"reviewId\": \"240a0658-dc5f-4878-9381-ebb7b2667772\"," +
                                "\"content\": \"안녕하세요\"," +
                                "\"attachedPhotoIds\": [\"e4d1a64e-a531-46de-88d0-ff0ed70c0bb8\", \"afb0cef2-851d-4a50-bb07-9cc15cbdc332\"]," +
                                "\"userId\": \"3ede0ef2-92b7-4817-a5f3-0c575361f745\"," +
                                "\"placeId\": \"2e4baf1c-5acb-4efb-a1af-eddada31b00f\"" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.code", is(ClientErrorCode.INVALID_REQUEST.getCode())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }
}
