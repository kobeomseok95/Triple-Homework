package com.triple.homework.history.adapter.out;

import com.triple.homework.history.application.port.out.PointHistoryPort;
import com.triple.homework.history.domain.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class PointHistoryRepository implements PointHistoryPort {

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    public PointHistory save(PointHistory pointHistory) {
        return pointHistoryJpaRepository.save(pointHistory);
    }
}
