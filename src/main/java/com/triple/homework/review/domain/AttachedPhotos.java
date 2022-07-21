package com.triple.homework.review.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class AttachedPhotos {

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "review",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<AttachedPhoto> attachedPhotos = new ArrayList<>();

    protected AttachedPhotos(List<AttachedPhoto> attachedPhotos) {
        this.attachedPhotos = attachedPhotos;
    }

    protected AttachedPhotos() {
    }

    private static List<AttachedPhoto> $default$attachedPhotos() {
        return new ArrayList<>();
    }

    public static AttachedPhotosBuilder builder() {
        return new AttachedPhotosBuilder();
    }

    public void add(Review review, List<String> attachedPhotoIds) {
        attachedPhotos.addAll(attachedPhotoIds.stream()
                .map(photoIds -> AttachedPhoto.from(review, photoIds))
                .collect(Collectors.toList()));
    }

    private void add(Review review, String attachedPhotoId) {
        attachedPhotos.add(AttachedPhoto.from(review, attachedPhotoId));
    }

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

    public void clear() {
        attachedPhotos.clear();
    }

    public List<AttachedPhoto> getAttachedPhotos() {
        return this.attachedPhotos;
    }

    public static class AttachedPhotosBuilder {
        private List<AttachedPhoto> attachedPhotos$value;
        private boolean attachedPhotos$set;

        AttachedPhotosBuilder() {
        }

        public AttachedPhotosBuilder attachedPhotos(List<AttachedPhoto> attachedPhotos) {
            this.attachedPhotos$value = attachedPhotos;
            this.attachedPhotos$set = true;
            return this;
        }

        public AttachedPhotos build() {
            List<AttachedPhoto> attachedPhotos$value = this.attachedPhotos$value;
            if (!this.attachedPhotos$set) {
                attachedPhotos$value = AttachedPhotos.$default$attachedPhotos();
            }
            return new AttachedPhotos(attachedPhotos$value);
        }

        public String toString() {
            return "AttachedPhotos.AttachedPhotosBuilder(attachedPhotos$value=" + this.attachedPhotos$value + ")";
        }
    }
}
