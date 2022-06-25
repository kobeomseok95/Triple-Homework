package com.triple.homework.review.domain;

import com.triple.homework.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "placeId"})
})
@SQLDelete(sql = "UPDATE REVIEW SET IS_DELETED = true WHERE REVIEW_ID = ?")
@Where(clause = "IS_DELETED=0")
public class Review extends BaseEntity {

    @Id
    @Column(name = "REVIEW_ID", length = 36)
    private String id;

    @Column(length = 36, nullable = false)
    private String userId;

    @Column(length = 36, nullable = false)
    private String placeId;

    @Column(length = 1000)
    private String content;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private boolean isDeleted;
}
