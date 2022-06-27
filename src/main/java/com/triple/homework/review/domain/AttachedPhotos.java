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
}
