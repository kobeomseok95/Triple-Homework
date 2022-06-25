package com.triple.homework.review.application.port.out;

import com.triple.homework.review.domain.Review;

public interface ReviewRepository {

    boolean existsByUserIdAndPlaceId(String userId, String placeId);

    Review save(Review review);

    boolean existsByPlaceId(String placeId);
}
