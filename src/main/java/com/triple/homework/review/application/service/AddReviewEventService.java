package com.triple.homework.review.application.service;

import com.triple.homework.common.exception.review.WrittenReviewByUserAndPlaceException;
import com.triple.homework.history.advisor.SavePointHistory;
import com.triple.homework.review.application.port.in.ReviewEventHandleUseCase;
import com.triple.homework.review.application.port.in.request.ReviewEventRequestDto;
import com.triple.homework.review.application.port.in.response.UserPointHistoryResponseDto;
import com.triple.homework.review.application.port.out.ReviewPort;
import com.triple.homework.review.domain.Review;
import com.triple.homework.user.application.port.out.UserPort;
import com.triple.homework.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
class AddReviewEventService implements ReviewEventHandleUseCase {

    private final ReviewPort reviewPort;
    private final CalculateReviewPointService calculateReviewPointService;
    private final UserPort userPort;

    @Override
    public String getCode() {
        return "ADD";
    }

    @Override
    @SavePointHistory
    public UserPointHistoryResponseDto handleEvent(ReviewEventRequestDto reviewEventRequestDto) {
        validateExistReview(reviewEventRequestDto);
        Long point = calculateReviewPointService.calculatePoint(reviewEventRequestDto);
        User user = findOrSave(reviewEventRequestDto.getUserId(), point);
        Review review = reviewPort.save(reviewEventRequestDto.toReview(user, point));
        review.addAttachedPhotos(reviewEventRequestDto.getAttachedPhotoIds());
        return UserPointHistoryResponseDto.of(user, point);
    }

    private void validateExistReview(ReviewEventRequestDto reviewEventRequestDto) {
        if (reviewPort.existsByUserIdAndPlaceId(reviewEventRequestDto.getUserId(), reviewEventRequestDto.getPlaceId())) {
            throw new WrittenReviewByUserAndPlaceException();
        }
    }

    // FIXME: 2022/07/10 kobeomseok95 적절한 위치는 아닌 것 같다.
    private User findOrSave(String userId, Long point) {
        Optional<User> user = userPort.findById(userId);
        if (user.isEmpty()) {
            return userPort.save(User.from(userId, point));
        }
        user.ifPresent(findUser -> findUser.calculate(point));
        return user.get();
    }
}
