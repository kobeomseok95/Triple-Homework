package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@SQLDelete(sql = "UPDATE ATTACHED_PHOTO SET IS_DELETED = true WHERE ATTACHED_PHOTO_ID = ?")
@Where(clause = "IS_DELETED=false")
public class AttachedPhoto extends BaseEntity {

    @Id
    @Column(name = "ATTACHED_PHOTO_ID", length = 36)
    private String id;

    @Column(nullable = false)
    private String reviewId;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private boolean isDeleted;
}
