package com.triple.homework.review.application.service;

import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.review.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
class CalculateReviewPointService {

    private final ReviewPort reviewPort;

    public Long calculatePoint(ReviewEventRequestDto requestDto) {
        Long point = 0L;
        if (contentGreaterThanOrEqualOne(requestDto.getContent())) {
            point += 1L;
        }
        if (photosGreaterThanOrEqualOne(requestDto.getAttachedPhotoIds())) {
            point += 1L;
        }
        if (isFirstReview(requestDto.getPlaceId())) {
            point += 1L;
        }
        return point;
    }

    private boolean contentGreaterThanOrEqualOne(String content) {
        return StringUtils.hasText(content);
    }

    private boolean photosGreaterThanOrEqualOne(List<String> attachedPhotoIds) {
        return attachedPhotoIds.size() >= 1 && StringUtils.hasText(attachedPhotoIds.get(0));
    }

    private boolean isFirstReview(String placeId) {
        return !reviewPort.existsByPlaceId(placeId);
    }

    public Long calculatePoint(Review review,
                               List<AttachedPhoto> attachedPhotos,
                               ReviewEventRequestDto reviewEventRequestDto) {

        Long point = 0L;
        if (modifyContentsLengthIsZero(review.getContent(), reviewEventRequestDto.getContent())) {
            point -= 1L;
        }
        if (modifyPhotosSizeIsZero(attachedPhotos, reviewEventRequestDto.getAttachedPhotoIds())) {
            point -= 1L;
        }
        return point;
    }

    private boolean modifyContentsLengthIsZero(String source, String target) {
        return contentGreaterThanOrEqualOne(source) && !contentGreaterThanOrEqualOne(target);
    }

    private boolean modifyPhotosSizeIsZero(List<AttachedPhoto> sources, List<String> targets) {
        return photosGreaterThanOrEqualOne(sources.stream()
                .map(AttachedPhoto::getId)
                .collect(Collectors.toList())) && !photosGreaterThanOrEqualOne(targets);
    }
}
