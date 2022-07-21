package com.triple.homework.history.domain;


import com.triple.homework.common.entity.BaseEntity;
import com.triple.homework.user.domain.User;

import javax.persistence.*;

@Entity
public class PointHistory extends BaseEntity {

    @Id
    @Column(name = "POINT_HISTORY_ID", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long point;

    protected PointHistory(String id, User user, Long point) {
        this.id = id;
        this.user = user;
        this.point = point;
    }

    protected PointHistory() {
    }

    public static PointHistoryBuilder builder() {
        return new PointHistoryBuilder();
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Long getPoint() {
        return this.point;
    }

    public static class PointHistoryBuilder {
        private String id;
        private User user;
        private Long point;

        PointHistoryBuilder() {
        }

        public PointHistoryBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PointHistoryBuilder user(User user) {
            this.user = user;
            return this;
        }

        public PointHistoryBuilder point(Long point) {
            this.point = point;
            return this;
        }

        public PointHistory build() {
            return new PointHistory(id, user, point);
        }

        public String toString() {
            return "PointHistory.PointHistoryBuilder(id=" + this.id + ", user=" + this.user + ", point=" + this.point + ")";
        }
    }
}
