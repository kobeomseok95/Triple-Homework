package com.triple.homework.review.adapter.in;

import com.triple.homework.common.exception.ClientErrorCode;
import com.triple.homework.common.exception.review.NotWrittenReviewException;
import com.triple.homework.common.exception.review.ReviewErrorCode;
import com.triple.homework.common.exception.review.WrittenReviewByUserAndPlaceException;
import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import com.triple.homework.review.adapter.in.request.ReviewEventRequestBuilder;
import com.triple.homework.support.RestControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
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
                .content(objectMapper.writeValueAsString(ReviewEventRequestBuilder.buildInvalidTypeAction()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.code", is(ClientErrorCode.INVALID_REQUEST.getCode())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    @DisplayName("리뷰 이벤트 ADD - 실패 / 유저가 장소에 대해 이미 작성한 리뷰인 경우")
    @Test
    void review_events_add_fail_exist_userId_and_placeId() throws Exception {

        // given
        ReviewEventRequest request = ReviewEventRequestBuilder.buildAdd();
        doThrow(new WrittenReviewByUserAndPlaceException())
                .when(reviewEventDelegator)
                .handle(any());

        // when, then
        mockMvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ReviewErrorCode.WRITTEN_REVIEW.getCode())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    @DisplayName("리뷰 이벤트 ADD - 성공")
    @Test
    void review_events_add_success() throws Exception {

        // given
        ReviewEventRequest request = ReviewEventRequestBuilder.buildAdd();

        // when, then
        mockMvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(reviewEventDelegator).handle(any(ReviewEventRequest.class));
    }

    @DisplayName("리뷰 이벤트 MOD - 실패 / 작성되지 않은 리뷰인 경우")
    @Test
    void review_events_mod_fail_not_written_review() throws Exception {

        // given
        ReviewEventRequest request = ReviewEventRequestBuilder.buildModify();
        doThrow(new NotWrittenReviewException())
                .when(reviewEventDelegator)
                .handle(any());

        // when, then
        mockMvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ReviewErrorCode.NOT_WRITTEN_REVIEW.getCode())))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())));
    }

    @DisplayName("리뷰 이벤트 MOD - 성공")
    @Test
    void review_events_mod_success() throws Exception {

        // given
        ReviewEventRequest request = ReviewEventRequestBuilder.buildModify();

        // when, then
        mockMvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(reviewEventDelegator).handle(any(ReviewEventRequest.class));
    }
}
