package com.triple.homework.history.adapter.out;

import com.triple.homework.history.application.port.out.PointHistoryPort;
import com.triple.homework.history.domain.PointHistory;
import org.springframework.stereotype.Repository;

@Repository
class PointHistoryRepository implements PointHistoryPort {

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    public PointHistoryRepository(PointHistoryJpaRepository pointHistoryJpaRepository) {
        this.pointHistoryJpaRepository = pointHistoryJpaRepository;
    }

    public PointHistory save(PointHistory pointHistory) {
        return pointHistoryJpaRepository.save(pointHistory);
    }
}
