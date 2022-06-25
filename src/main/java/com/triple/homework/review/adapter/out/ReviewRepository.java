package com.triple.homework.review.adapter.out;

import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ReviewRepository implements ReviewPort {

    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public boolean existsByUserIdAndPlaceId(String userId, String placeId) {
        return reviewJpaRepository.existsByUserIdAndPlaceId(userId, placeId);
    }

    @Override
    public Review save(Review review) {
        return reviewJpaRepository.save(review);
    }

    @Override
    public boolean existsByPlaceId(String placeId) {
        return reviewJpaRepository.existsByPlaceId(placeId);
    }
}
