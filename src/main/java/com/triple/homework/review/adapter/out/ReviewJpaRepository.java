package com.triple.homework.review.adapter.out;

import com.triple.homework.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// FIXME: 2022/07/13 kobeomseok95 동시성 제어 관련한 LOCK 의 필요성
interface ReviewJpaRepository extends JpaRepository<Review, String> {

    boolean existsByUserIdAndPlaceId(String userId, String placeId);

    boolean existsByPlaceId(String placeId);

    @Query(
            "select distinct r " +
            "from Review r " +
            "join fetch r.user " +
            "left join fetch r.attachedPhotos.attachedPhotos a " +
            "where r.id = :reviewId"
    )
    Optional<Review> findByIdWithUserAttachedPhotos(@Param("reviewId") String reviewId);
}
