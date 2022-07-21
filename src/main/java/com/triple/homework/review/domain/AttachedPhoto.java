package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
public class AttachedPhoto extends BaseEntity {

    @Id
    @Column(name = "ATTACHED_PHOTO_ID", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID", nullable = false)
    private Review review;

    protected AttachedPhoto(String id, Review review) {
        this.id = id;
        this.review = review;
    }

    protected AttachedPhoto() {
    }

    public static AttachedPhoto from(Review review, String attachedPhotoId) {
        return AttachedPhoto.builder()
                .id(attachedPhotoId)
                .review(review)
                .build();
    }

    public static AttachedPhotoBuilder builder() {
        return new AttachedPhotoBuilder();
    }

    public void delete() {
        this.review = null;
    }

    public String getId() {
        return this.id;
    }

    public Review getReview() {
        return this.review;
    }

    public static class AttachedPhotoBuilder {
        private String id;
        private Review review;

        AttachedPhotoBuilder() {
        }

        public AttachedPhotoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AttachedPhotoBuilder review(Review review) {
            this.review = review;
            return this;
        }

        public AttachedPhoto build() {
            return new AttachedPhoto(id, review);
        }

        public String toString() {
            return "AttachedPhoto.AttachedPhotoBuilder(id=" + this.id + ", review=" + this.review + ")";
        }
    }
}
