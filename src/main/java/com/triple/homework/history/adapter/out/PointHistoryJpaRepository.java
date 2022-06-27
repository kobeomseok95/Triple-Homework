package com.triple.homework.history.adapter.out;

import com.triple.homework.history.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

interface PointHistoryJpaRepository extends JpaRepository<PointHistory, String> {
}
