package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import com.triple.homework.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void addAttachedPhotos(List<String> attachedPhotoIds) {
        if (attachedPhotos == null) {
            attachedPhotos = new AttachedPhotos();
        }
        attachedPhotos.add(this, attachedPhotoIds);
    }

    public void modify(Long point,
                       String content,
                       String placeId,
                       List<String> attachedPhotoIds) {
        // TODO: 2022/06/27 kobeomseok95 implement
    }
}
