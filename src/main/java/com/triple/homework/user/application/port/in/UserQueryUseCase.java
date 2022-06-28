package com.triple.homework.user.application.port.in;

import com.triple.homework.user.application.port.in.response.UserPointResponseDto;

public interface UserQueryUseCase {

    UserPointResponseDto findById(String userId);
}
