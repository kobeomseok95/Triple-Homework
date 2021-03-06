package com.triple.homework.review.adapter.out;

import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class ReviewRepository implements ReviewPort {

    private final ReviewJpaRepository reviewJpaRepository;

    public ReviewRepository(ReviewJpaRepository reviewJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
    }

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

    @Override
    public Optional<Review> findByIdWithUserAttachedPhotos(String reviewId) {
        return reviewJpaRepository.findByIdWithUserAttachedPhotos(reviewId);
    }

    @Override
    public void delete(Review review) {
        reviewJpaRepository.delete(review);
    }
}
