package com.triple.homework.history.application.port.out;

import com.triple.homework.history.domain.PointHistory;

public interface PointHistoryPort {

    PointHistory save(PointHistory pointHistory);
}
