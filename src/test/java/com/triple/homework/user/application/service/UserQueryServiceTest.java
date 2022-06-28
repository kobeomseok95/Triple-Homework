package com.triple.homework.user.application.service;

import com.triple.homework.common.exception.user.UserNotFoundException;
import com.triple.homework.fixture.UserFixture;
import com.triple.homework.user.application.port.in.response.UserPointResponseDto;
import com.triple.homework.user.application.port.out.UserPort;
import com.triple.homework.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @Mock UserPort userPort;
    @InjectMocks UserQueryService userQueryService;

    @DisplayName("유저의 포인트 조회 - 실패 / 존재하지 않을 경우")
    @Test
    void findById_fail_not_found_user() throws Exception {

        // given
        when(userPort.findById(any()))
                .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> userQueryService.findById(UUID.randomUUID().toString()))
                .isInstanceOf(UserNotFoundException.class);
    }

    @DisplayName("유저의 포인트 조회 - 성공")
    @Test
    void findById_success() throws Exception {

        // given
        User user = UserFixture.user();
        when(userPort.findById(any()))
                .thenReturn(Optional.of(user));

        // when
        UserPointResponseDto responseDto = userQueryService.findById(user.getId());
        assertAll(
                () -> verify(userPort).findById(user.getId()),
                () -> assertThat(responseDto.getUserId()).isEqualTo(user.getId()),
                () -> assertThat(responseDto.getPoint()).isEqualTo(user.getUserPoints())
        );
    }
}
