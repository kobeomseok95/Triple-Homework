package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import com.triple.homework.common.exception.review.PlaceIdIsDifferentException;
import com.triple.homework.user.domain.User;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_ID", "PLACE_ID"})
})
public class Review extends BaseEntity {

    @Id
    @Column(name = "REVIEW_ID", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Embedded
    private AttachedPhotos attachedPhotos;

    @Column(length = 36, nullable = false, name = "PLACE_ID")
    private String placeId;

    @Column(length = 1000)
    private String content;

    @Column(nullable = false)
    private Long reviewPoints;

    public Review(String id, User user, AttachedPhotos attachedPhotos, String placeId, String content, Long reviewPoints) {
        this.id = id;
        this.user = user;
        this.attachedPhotos = attachedPhotos;
        this.placeId = placeId;
        this.content = content;
        this.reviewPoints = reviewPoints;
    }

    public Review() {
    }

    public static ReviewBuilder builder() {
        return new ReviewBuilder();
    }

    public void addAttachedPhotos(List<String> attachedPhotoIds) {
        if (attachedPhotos == null) {
            attachedPhotos = new AttachedPhotos();
        }
        attachedPhotos.add(this, attachedPhotoIds);
    }

    public Long modify(String content,
                       String placeId,
                       List<String> attachedPhotoIds) {
        checkPlaceId(placeId);
        Long changePoint = compareContentAttachedPhotosAndChangeUserPoints(content, attachedPhotoIds);
        this.content = content;
        attachedPhotos.update(this, attachedPhotoIds);
        return changePoint;
    }

    private void checkPlaceId(String placeId) {
        if (!this.placeId.equals(placeId)) {
            throw new PlaceIdIsDifferentException();
        }
    }

    private Long compareContentAttachedPhotosAndChangeUserPoints(String content, List<String> attachedPhotoIds) {
        Long changePoint = 0L;
        // FIXME: 2022/07/11 kobeomseok95 유의미한 메서드로 따로 빼보는건 어떨까?
        if (StringUtils.hasText(this.content) && !StringUtils.hasText(content)) {
            changePoint -= 1L;
        } else if (!StringUtils.hasText(this.content) && StringUtils.hasText(content)) {
            changePoint += 1L;
        }

        // FIXME: 2022/07/11 kobeomseok95 유의미한 메서드로 따로 빼보는건 어떨까?
        if (!this.attachedPhotos.isEmpty() && attachedPhotoIds.isEmpty()) {
            changePoint -= 1L;
        } else if(this.attachedPhotos.isEmpty() && !attachedPhotoIds.isEmpty()) {
            changePoint += 1L;
        }

        user.calculate(changePoint);
        reviewPoints += changePoint;
        return changePoint;
    }

    public void decreaseUsersPointAndReturnReviewPoint() {
        user.calculate(-reviewPoints);
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public AttachedPhotos getAttachedPhotos() {
        return this.attachedPhotos;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public String getContent() {
        return this.content;
    }

    public Long getReviewPoints() {
        return this.reviewPoints;
    }

    public static class ReviewBuilder {
        private String id;
        private User user;
        private AttachedPhotos attachedPhotos;
        private String placeId;
        private String content;
        private Long reviewPoints;

        ReviewBuilder() {
        }

        public ReviewBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ReviewBuilder user(User user) {
            this.user = user;
            return this;
        }

        public ReviewBuilder attachedPhotos(AttachedPhotos attachedPhotos) {
            this.attachedPhotos = attachedPhotos;
            return this;
        }

        public ReviewBuilder placeId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public ReviewBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ReviewBuilder reviewPoints(Long reviewPoints) {
            this.reviewPoints = reviewPoints;
            return this;
        }

        public Review build() {
            return new Review(id, user, attachedPhotos, placeId, content, reviewPoints);
        }

        public String toString() {
            return "Review.ReviewBuilder(id=" + this.id + ", user=" + this.user + ", attachedPhotos=" + this.attachedPhotos + ", placeId=" + this.placeId + ", content=" + this.content + ", reviewPoints=" + this.reviewPoints + ")";
        }
    }
}
