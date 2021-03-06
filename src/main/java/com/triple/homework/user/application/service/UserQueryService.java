package com.triple.homework.user.application.service;

import com.triple.homework.common.exception.user.UserNotFoundException;
import com.triple.homework.user.application.port.in.UserQueryUseCase;
import com.triple.homework.user.application.port.in.response.UserPointResponseDto;
import com.triple.homework.user.application.port.out.UserPort;
import com.triple.homework.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
class UserQueryService implements UserQueryUseCase {

    private final UserPort userPort;

    public UserQueryService(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public UserPointResponseDto findById(String userId) {
        User user = userPort.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return UserPointResponseDto.from(user);
    }
}
