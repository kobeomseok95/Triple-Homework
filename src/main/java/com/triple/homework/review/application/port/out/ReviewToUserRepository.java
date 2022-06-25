package com.triple.homework.review.application.port.out;

import com.triple.homework.user.domain.User;

import java.util.Optional;

public interface ReviewToUserRepository {

    Optional<User> findById(String userId);

    User save(User user);
}
