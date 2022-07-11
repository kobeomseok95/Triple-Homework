package com.triple.homework.review.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AttachedPhotos {

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "review",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    @Builder.Default
    private List<AttachedPhoto> attachedPhotos = new ArrayList<>();

    public void add(Review review, List<String> attachedPhotoIds) {
        attachedPhotos.addAll(attachedPhotoIds.stream()
                .map(photoIds -> AttachedPhoto.from(review, photoIds))
                .collect(Collectors.toList()));
    }

    private void add(Review review, String attachedPhotoId) {
        attachedPhotos.add(AttachedPhoto.from(review, attachedPhotoId));
    }

    // FIXME: 2022/07/11 kobeomseok95 Set 자료구조를 이용한다면?
    public void update(Review review, List<String> newAttachedPhotoIds) {
        List<String> sourceAttachedPhotoIds = attachedPhotos.stream()
                .map(AttachedPhoto::getId)
                .collect(Collectors.toList());

        newAttachedPhotoIds.forEach(newAttachedPhotoId -> {
            if (!sourceAttachedPhotoIds.contains(newAttachedPhotoId)) {
                add(review, newAttachedPhotoId);
            }
        });

        List<AttachedPhoto> removeAttachedPhotos = attachedPhotos.stream()
                .filter(addedAttachedPhoto -> !newAttachedPhotoIds.contains(addedAttachedPhoto.getId()))
                .collect(Collectors.toList());
        attachedPhotos.removeAll(removeAttachedPhotos);
        removeAttachedPhotos.forEach(AttachedPhoto::delete);
    }

    public boolean isEmpty() {
        return attachedPhotos.isEmpty();
    }
}
