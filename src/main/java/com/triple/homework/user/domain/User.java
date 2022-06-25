package com.triple.homework.user.domain;

import com.triple.homework.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @Column(name = "USER_ID", length = 36)
    private String id;

    @Column(nullable = false)
    private Long pointScore;

    public static User from(String userId, Long point) {
        return User.builder()
                .id(userId)
                .pointScore(point)
                .build();
    }

    public void calculate(Long point) {
        pointScore += point;
    }
}
