package com.triple.homework.review.adapter.in;

import com.triple.homework.common.exception.review.EventActionNotFoundException;
import com.triple.homework.review.adapter.in.request.ReviewEventRequest;
import com.triple.homework.review.adapter.in.request.ReviewEventRequestBuilder;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewEventDelegatorTest {

    @InjectMocks ReviewEventDelegator reviewEventDelegator;
    @Spy List<ReviewEventHandleUseCase> useCases = new ArrayList<>();
    @Mock ReviewEventHandleUseCase addReviewEventHandler;
    @Mock ReviewEventHandleUseCase modifyReviewEventHandler;
    @Mock ReviewEventHandleUseCase deleteReviewEventHandler;

    @BeforeEach
    void setUp() {
        useCases.add(addReviewEventHandler);
        useCases.add(modifyReviewEventHandler);
        useCases.add(deleteReviewEventHandler);
    }

    @AfterEach
    void tearDown() {
        useCases.clear();
    }

    @DisplayName("ADD 이벤트 발생")
    @Test
    void add_event() throws Exception {

        // given
        when(addReviewEventHandler.getCode()).thenReturn("ADD");
        ReviewEventRequest request = ReviewEventRequestBuilder.buildAdd();

        // when
        reviewEventDelegator.handle(request);

        // then
        verify(addReviewEventHandler).handleEvent(any(ReviewEventRequestDto.class));
    }

    @DisplayName("MOD 이벤트 발생")
    @Test
    void mod_event() throws Exception {

        // given
        when(addReviewEventHandler.getCode()).thenReturn("ADD");
        when(modifyReviewEventHandler.getCode()).thenReturn("MOD");
        ReviewEventRequest request = ReviewEventRequestBuilder.buildModify();

        // when
        reviewEventDelegator.handle(request);

        // then
        verify(modifyReviewEventHandler).handleEvent(any(ReviewEventRequestDto.class));
    }

    @DisplayName("DELETE 이벤트 발생")
    @Test
    void delete_event() throws Exception {

        // given
        when(addReviewEventHandler.getCode()).thenReturn("ADD");
        when(modifyReviewEventHandler.getCode()).thenReturn("MOD");
        when(deleteReviewEventHandler.getCode()).thenReturn("DELETE");
        ReviewEventRequest request = ReviewEventRequestBuilder.buildDelete();

        // when
        reviewEventDelegator.handle(request);

        // then
        verify(deleteReviewEventHandler).handleEvent(any(ReviewEventRequestDto.class));
    }

    @DisplayName("이벤트가 없을 경우 예외 발생")
    @Test
    void event_not_found_throw_exception() throws Exception {

        // given
        when(addReviewEventHandler.getCode()).thenReturn("ADD");
        when(modifyReviewEventHandler.getCode()).thenReturn("MOD");
        when(deleteReviewEventHandler.getCode()).thenReturn("DELETE");
        ReviewEventRequest request = ReviewEventRequestBuilder.buildInvalidTypeAction();

        // when, then
        assertThrows(EventActionNotFoundException.class,
                () -> reviewEventDelegator.handle(request));
    }
}
