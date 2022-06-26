package com.triple.homework.user.application.port.out;

import com.triple.homework.user.domain.User;

import java.util.Optional;

public interface UserPort {

    Optional<User> findById(String userId);

    User save(User user);
}
