package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.WrittenReviewByUserAndPlaceException;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.out.AttachedPhotoPort;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.application.port.out.ReviewToUserPort;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class AddReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewPort reviewPort;
    private final CalculateReviewPointService calculateReviewPointService;
    private final ReviewToUserPort userRepository;
    private final AttachedPhotoPort attachedPhotoPort;

    @Override
    public String getCode() {
        return "ADD";
    }

    @Override
    public void handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        validateExistReview(reviewEventRequestDto);
        Long point = calculateReviewPointService.calculatePoint(reviewEventRequestDto);
        userRepository.findById(reviewEventRequestDto.getUserId())
                .ifPresentOrElse(
                        user -> user.calculate(point),
                        () -> userRepository.save(User.from(reviewEventRequestDto.getUserId(), point)));
        reviewPort.save(reviewEventRequestDto.toReview());
        attachedPhotoPort.saveAll(AttachedPhoto.from(reviewEventRequestDto.getReviewId(),
                reviewEventRequestDto.getAttachedPhotoIds()));
    }

    private void validateExistReview(ReviewEventRequestDto reviewEventRequestDto) {
        if (reviewPort.existsByUserIdAndPlaceId(reviewEventRequestDto.getUserId(), reviewEventRequestDto.getPlaceId())) {
            throw new WrittenReviewByUserAndPlaceException();
        }
    }
}
