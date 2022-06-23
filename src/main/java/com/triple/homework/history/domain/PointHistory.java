package com.triple.homework.history.domain;


import com.triple.homework.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PointHistory extends BaseEntity {

    @Id
    @Column(name = "POINT_HISTORY_ID", length = 36)
    private String id;

    @Column(nullable = false)
    private Long point;

    @Column(nullable = false)
    private String userId;
}
