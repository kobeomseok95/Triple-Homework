package com.triple.homework.review.application.port.out;

import com.triple.homework.review.domain.Review;

import java.util.Optional;

public interface ReviewPort {

    boolean existsByUserIdAndPlaceId(String userId, String placeId);

    Review save(Review review);

    boolean existsByPlaceId(String placeId);

    Optional<Review> findByIdWithUserAttachedPhotos(String reviewId);

    void delete(Review review);
}
