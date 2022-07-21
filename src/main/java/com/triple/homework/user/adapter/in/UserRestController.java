package com.triple.homework.user.adapter.in;

import com.triple.homework.user.adapter.in.response.UserPointResponse;
import com.triple.homework.user.application.port.in.UserQueryUseCase;
import com.triple.homework.user.application.port.in.response.UserPointResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}")
public class UserRestController {

    private final UserQueryUseCase userQueryUseCase;

    public UserRestController(UserQueryUseCase userQueryUseCase) {
        this.userQueryUseCase = userQueryUseCase;
    }

    @GetMapping("/points")
    @ResponseStatus(HttpStatus.OK)
    public UserPointResponse getUserPoints(@PathVariable String userId) {
        UserPointResponseDto responseDto = userQueryUseCase.findById(userId);
        return UserPointResponse.from(responseDto);
    }
}
