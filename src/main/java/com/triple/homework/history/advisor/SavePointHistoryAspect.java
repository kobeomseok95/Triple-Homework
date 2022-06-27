package com.triple.homework.history.advisor;

import com.triple.homework.history.application.port.out.PointHistoryPort;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
class SavePointHistoryAspect {

    private final PointHistoryPort pointHistoryPort;

    @AfterReturning(value = "@annotation(com.triple.homework.history.advisor.SavePointHistory)",
            returning = "userPointHistoryResponseDto")
    public void savePointHistory(JoinPoint joinPoint, UserPointHistoryResponseDto userPointHistoryResponseDto) {
        if (userPointHistoryResponseDto.getChangedPoint() != 0) {
            pointHistoryPort.save(userPointHistoryResponseDto.toPointHistory());
        }
    }
}
