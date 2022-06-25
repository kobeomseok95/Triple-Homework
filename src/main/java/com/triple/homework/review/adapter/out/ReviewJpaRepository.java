package com.triple.homework.review.adapter.out;

import com.triple.homework.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

interface ReviewJpaRepository extends JpaRepository<Review, String> {

    boolean existsByUserIdAndPlaceId(String userId, String placeId);

    boolean existsByPlaceId(String placeId);
}
