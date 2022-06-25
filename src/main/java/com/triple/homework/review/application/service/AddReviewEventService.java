package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.WrittenReviewByUserAndPlaceException;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.out.AttachedPhotoRepository;
import com.triple.homework.review.application.port.out.ReviewRepository;
import com.triple.homework.review.application.port.out.ReviewToUserRepository;
import com.triple.homework.review.domain.AttachedPhoto;
import com.triple.homework.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class AddReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewRepository reviewRepository;
    private final CalculateReviewPointService calculateReviewPointService;
    private final ReviewToUserRepository userRepository;
    private final AttachedPhotoRepository attachedPhotoRepository;

    @Override
    public String getCode() {
        return "ADD";
    }

    @Override
    public void handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        // TODO: 2022/06/25 kobeomseok95 Validator 계층으로 분리
        if (reviewRepository.existsByUserIdAndPlaceId(reviewEventRequestDto.getUserId(), reviewEventRequestDto.getPlaceId())) {
            throw new WrittenReviewByUserAndPlaceException();
        }
        Long point = calculateReviewPointService.calculatePoint(reviewEventRequestDto);
        userRepository.findById(reviewEventRequestDto.getUserId())
                .ifPresentOrElse(
                        user -> user.calculate(point),
                        () -> userRepository.save(User.from(reviewEventRequestDto.getUserId(), point)));
        reviewRepository.save(reviewEventRequestDto.toReview());
        attachedPhotoRepository.saveAll(AttachedPhoto.from(reviewEventRequestDto.getReviewId(),
                reviewEventRequestDto.getAttachedPhotoIds()));
    }
}
