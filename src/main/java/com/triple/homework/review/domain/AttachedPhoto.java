package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AttachedPhoto extends BaseEntity {

    @Id
    @Column(name = "ATTACHED_PHOTO_ID", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID", nullable = false)
    private Review review;

    public static AttachedPhoto from(Review review, String attachedPhotoId) {
        return AttachedPhoto.builder()
                .id(attachedPhotoId)
                .review(review)
                .build();
    }

    public void delete() {
        this.review = null;
    }
}
