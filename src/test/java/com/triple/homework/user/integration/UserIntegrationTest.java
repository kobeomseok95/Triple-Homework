package com.triple.homework.user.integration;

import com.triple.homework.support.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserIntegrationTest extends IntegrationTest {

    @Sql("classpath:review-test.sql")
    @DisplayName("유저의 포인트 조회 - 성공")
    @Test
    void get_user_point_success() throws Exception {

        // given
        String userId = "3ede0ef2-92b7-4817-a5f3-0c575361f745";

        // when, then
        mockMvc.perform(get("/users/{userId}/points", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(userId)))
                .andExpect(jsonPath("$.point", is(3)));
    }
}
