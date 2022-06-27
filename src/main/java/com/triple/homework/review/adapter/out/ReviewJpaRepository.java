package com.triple.homework.review.adapter.out;

import com.triple.homework.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface ReviewJpaRepository extends JpaRepository<Review, String> {

    boolean existsByUserIdAndPlaceId(String userId, String placeId);

    boolean existsByPlaceId(String placeId);

    @Query(
            "select distinct r " +
            "from Review r " +
            "join fetch r.user " +
            "join fetch r.attachedPhotos.attachedPhotos a " +
            "where r.id = :reviewId"
    )
    Optional<Review> findByIdWithUserAttachedPhotos(@Param("reviewId") String reviewId);
}
