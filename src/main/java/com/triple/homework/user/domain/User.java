package com.triple.homework.user.domain;

import com.triple.homework.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @Id
    @Column(name = "USER_ID", length = 36)
    private String id;

    @Column(nullable = false)
    private Long userPoints;

    public User(String id, Long userPoints) {
        this.id = id;
        this.userPoints = userPoints;
    }

    public User() {
    }

    public static User from(String userId, Long point) {
        return User.builder()
                .id(userId)
                .userPoints(point)
                .build();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public void calculate(Long point) {
        userPoints += point;
    }

    public String getId() {
        return this.id;
    }

    public Long getUserPoints() {
        return this.userPoints;
    }

    public static class UserBuilder {
        private String id;
        private Long userPoints;

        UserBuilder() {
        }

        public UserBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder userPoints(Long userPoints) {
            this.userPoints = userPoints;
            return this;
        }

        public User build() {
            return new User(id, userPoints);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", userPoints=" + this.userPoints + ")";
        }
    }
}
