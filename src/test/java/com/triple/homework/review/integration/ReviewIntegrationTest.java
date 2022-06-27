package com.triple.homework.review.integration;

import com.triple.homework.common.exception.ClientErrorCode;
import com.triple.homework.common.exception.review.ReviewErrorCode;
import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import com.triple.homework.review.adapter.in.request.ReviewEventRequestBuilder;
import com.triple.homework.user.application.port.out.UserPort;
import com.triple.homework.support.IntegrationTest;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReviewIntegrationTest extends IntegrationTest {

    @Autowired UserPort userPort;

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
                .content(objectMapper.writeValueAsString(ReviewEventRequestBuilder.buildInvalidTypeAction()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.code", is(ClientErrorCode.INVALID_REQUEST.getCode())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    @Sql("classpath:add-review-test.sql")
    @DisplayName("리뷰 이벤트 ADD - 실패 / 유저가 장소에 대해 이미 작성한 리뷰인 경우")
    @Test
    void review_events_add_fail_exist_userId_and_placeId() throws Exception {

        // given
        ReviewEventRequest request = ReviewEventRequestBuilder.buildAdd();

        // when, then
        mockMvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ReviewErrorCode.WRITTEN_REVIEW.getCode())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    @DisplayName("리뷰 이벤트 ADD - 성공 / 1글자 이상의 내용, 1장 이상의 사진, 장소에 대한 첫 리뷰인 경우 3점의 포인트 추가")
    @Test
    void review_events_add_success() throws Exception {

        // given
        ReviewEventRequest request = ReviewEventRequestBuilder.buildAdd();

        // when, then
        mockMvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        flushAndClear();

        User user = userPort.findById(request.getUserId()).get();
        assertThat(user.getUserPoints()).isEqualTo(3L);
    }
}
