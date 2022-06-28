package com.triple.homework.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.homework.common.exception.GlobalExceptionHandler;
import com.triple.homework.review.adapter.in.ReviewEventDelegator;
import com.triple.homework.review.adapter.in.ReviewEventRestController;
import com.triple.homework.user.adapter.in.UserRestController;
import com.triple.homework.user.application.port.in.UserQueryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = {
        GlobalExceptionHandler.class,
        ReviewEventRestController.class,
        UserRestController.class,
})
public abstract class RestControllerTest {

    @MockBean protected ReviewEventDelegator reviewEventDelegator;
    @MockBean protected UserQueryUseCase userQueryUseCase;
    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;
}
