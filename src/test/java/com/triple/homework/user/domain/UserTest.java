package com.triple.homework.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(UUID.randomUUID().toString())
                .userPoints(0L)
                .build();
    }

    @DisplayName("포인트 내역 반영 - 증가할 경우")
    @Test
    void plus_point_when_user_add_review() throws Exception {

        // given
        Long userPoints = user.getUserPoints();
        Long plusPoint = 2L;

        // when
        user.calculate(plusPoint);

        // then
        assertThat(user.getUserPoints()).isEqualTo(userPoints + plusPoint);
    }

    @DisplayName("포인트 내역 반영 - 감소할 경우")
    @Test
    void minus_point_when_user_add_review() throws Exception {

        // given
        Long userPoints = user.getUserPoints();
        Long plusPoint = -2L;

        // when
        user.calculate(plusPoint);

        // then
        assertThat(user.getUserPoints()).isEqualTo(userPoints + plusPoint);
    }
}
