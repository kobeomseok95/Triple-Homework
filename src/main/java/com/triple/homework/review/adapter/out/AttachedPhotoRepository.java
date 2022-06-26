package com.triple.homework.review.adapter.out;

import com.triple.homework.review.application.port.out.AttachedPhotoPort;
import com.triple.homework.review.domain.AttachedPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class AttachedPhotoRepository implements AttachedPhotoPort {

    private final AttachedPhotoJpaRepository attachedPhotoJpaRepository;

    @Override
    public List<AttachedPhoto> saveAll(Iterable<AttachedPhoto> attachedPhotos) {
        return attachedPhotoJpaRepository.saveAll(attachedPhotos);
    }

    @Override
    public List<AttachedPhoto> findByReviewId(String reviewId) {
        // TODO: 2022/06/26 kobeomseok95 implements
        return null;
    }
}
