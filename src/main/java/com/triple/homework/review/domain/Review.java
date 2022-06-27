package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import com.triple.homework.common.exception.review.PlaceIdIsDifferentException;
import com.triple.homework.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public void addAttachedPhotos(List<String> attachedPhotoIds) {
        if (attachedPhotos == null) {
            attachedPhotos = new AttachedPhotos();
        }
        attachedPhotos.add(this, attachedPhotoIds);
    }

    public void modify(String content,
                       String placeId,
                       List<String> attachedPhotoIds) {
        checkPlaceId(placeId);
        compareContentAttachedPhotosAndChangeUserPoints(content, attachedPhotoIds);
        this.content = content;
        attachedPhotos.put(this, attachedPhotoIds);
    }

    private void checkPlaceId(String placeId) {
        if (!this.placeId.equals(placeId)) {
            throw new PlaceIdIsDifferentException();
        }
    }

    private void compareContentAttachedPhotosAndChangeUserPoints(String content, List<String> attachedPhotoIds) {
        Long changePoint = 0L;
        if (StringUtils.hasText(this.content) && !StringUtils.hasText(content)) {
            changePoint -= 1L;
        } else if (!StringUtils.hasText(this.content) && StringUtils.hasText(content)) {
            changePoint += 1L;
        }

        if (!this.attachedPhotos.isEmpty() && attachedPhotoIds.isEmpty()) {
            changePoint -= 1L;
        } else if(this.attachedPhotos.isEmpty() && !attachedPhotoIds.isEmpty()) {
            changePoint += 1L;
        }

        user.calculate(changePoint);
        reviewPoints += changePoint;
    }

    public void decreaseUsersPointAndReturnReviewPoint() {
        user.calculate(-reviewPoints);
    }
}
