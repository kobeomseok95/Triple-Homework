package com.triple.homework.review.adapter.out;

import com.triple.homework.review.domain.AttachedPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface AttachedPhotoJpaRepository extends JpaRepository<AttachedPhoto, String> {

    List<AttachedPhoto> findByReviewId(String reviewId);
}
