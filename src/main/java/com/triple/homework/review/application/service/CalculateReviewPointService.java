package com.triple.homework.review.application.service;

import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class CalculateReviewPointService {

    private final ReviewPort reviewPort;

    public Long calculatePointForAddReview(ReviewEventRequestDto requestDto) {
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
}
