package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AttachedPhoto extends BaseEntity {

    @Id
    @Column(name = "ATTACHED_PHOTO_ID", length = 36)
    private String id;

    @Column(nullable = false)
    private String reviewId;

    public static List<AttachedPhoto> from(String reviewId, List<String> attachedPhotoIds) {
        return attachedPhotoIds.stream()
                .map(attachedPhotoId -> from(reviewId, attachedPhotoId))
                .collect(Collectors.toList());
    }

    private static AttachedPhoto from(String reviewId, String attachedPhotoId) {
        return AttachedPhoto.builder()
                .id(attachedPhotoId)
                .reviewId(reviewId)
                .build();
    }
}
