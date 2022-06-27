package com.triple.homework.history.domain;


import com.triple.homework.common.entity.BaseEntity;
import com.triple.homework.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PointHistory extends BaseEntity {

    @Id
    @Column(name = "POINT_HISTORY_ID", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long point;
}
