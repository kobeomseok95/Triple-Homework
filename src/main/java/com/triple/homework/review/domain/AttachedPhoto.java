package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@SQLDelete(sql = "UPDATE ATTACHED_PHOTO SET IS_DELETED = true WHERE ATTACHED_PHOTO_ID = ?")
@Where(clause = "IS_DELETED=0")
public class AttachedPhoto extends BaseEntity {

    @Id
    @Column(name = "ATTACHED_PHOTO_ID", length = 36)
    private String id;

    @Column(nullable = false)
    private String reviewId;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private boolean isDeleted;

    public static List<AttachedPhoto> from(String reviewId, List<String> attachedPhotoIds) {
        return attachedPhotoIds.stream()
                .map(attachedPhotoId -> from(reviewId, attachedPhotoId))
                .collect(Collectors.toList());
    }

    private static AttachedPhoto from(String reviewId, String attachedPhotoId) {
        return AttachedPhoto.builder()
                .id(attachedPhotoId)
                .reviewId(reviewId)
                .isDeleted(false)
                .build();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
