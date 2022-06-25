package com.triple.homework.review.application.port.out;

import com.triple.homework.review.domain.AttachedPhoto;

import java.util.List;

public interface AttachedPhotoPort {

    List<AttachedPhoto> saveAll(Iterable<AttachedPhoto> attachedPhotos);
}
