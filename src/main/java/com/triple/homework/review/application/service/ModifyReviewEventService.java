package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.NotWrittenReviewException;
import com.triple.homework.common.exception.user.UserNotFoundException;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.out.AttachedPhotoPort;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.review.domain.Review;
import com.triple.homework.user.application.port.out.UserPort;
import com.triple.homework.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class ModifyReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewPort reviewPort;
    private final UserPort userPort;
    private final AttachedPhotoPort attachedPhotoPort;
    private final CalculateReviewPointService calculateReviewPointService;

    @Override
    public String getCode() {
        return "MOD";
    }

    @Override
    public void handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        Review review = reviewPort.findById(reviewEventRequestDto.getReviewId())
                .orElseThrow(NotWrittenReviewException::new);
        User user = userPort.findById(reviewEventRequestDto.getUserId())
                .orElseThrow(UserNotFoundException::new);
        List<AttachedPhoto> attachedPhotos = attachedPhotoPort.findByReviewId(reviewEventRequestDto.getReviewId());
        Long differencePoint = calculateReviewPointService.calculatePoint(review, attachedPhotos, reviewEventRequestDto);

        review.changeContent(reviewEventRequestDto.getContent());
        user.calculate(differencePoint);
        // TODO: 2022/06/27 kobeomseok95 delete before attachedPhotos
        attachedPhotoPort.saveAll(AttachedPhoto.from(reviewEventRequestDto.getReviewId(),
                reviewEventRequestDto.getAttachedPhotoIds()));
    }
}
