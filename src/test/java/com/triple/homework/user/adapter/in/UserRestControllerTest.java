package com.triple.homework.user.adapter.in;

import com.triple.homework.common.exception.user.UserErrorCode;
import com.triple.homework.common.exception.user.UserNotFoundException;
import com.triple.homework.support.RestControllerTest;
import com.triple.homework.user.application.port.in.response.UserPointResponseDto;
import com.triple.homework.user.application.port.in.response.UserPointResponseDtoBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestControllerTest extends RestControllerTest {

    @DisplayName("유저의 포인트 조회 - 실패 / 존재하지 않는 유저일 경우")
    @Test
    void get_user_point_fail_not_found_user() throws Exception {

        // given
        when(userQueryUseCase.findById(any()))
                .thenThrow(new UserNotFoundException());

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}/points", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.code", is(UserErrorCode.NOT_FOUND_USER.getCode())));
    }

    @DisplayName("유저의 포인트 조회 - 성공")
    @Test
    void get_user_point_success() throws Exception {

        // given
        UserPointResponseDto responseDto = UserPointResponseDtoBuilder.build();
        when(userQueryUseCase.findById(any()))
                .thenReturn(responseDto);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}/points", responseDto.getUserId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(responseDto.getUserId())))
                .andExpect(jsonPath("$.point", is(responseDto.getPoint().intValue())));
    }
}
