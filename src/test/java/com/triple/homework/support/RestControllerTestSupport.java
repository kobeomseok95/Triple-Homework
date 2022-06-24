package com.triple.homework.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.homework.common.exception.GlobalExceptionHandler;
import com.triple.homework.review.adapter.in.ReviewEventRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = {
        GlobalExceptionHandler.class,
        ReviewEventRestController.class,
})
public abstract class RestControllerTestSupport {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;
}
