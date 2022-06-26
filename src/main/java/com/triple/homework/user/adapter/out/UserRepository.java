package com.triple.homework.user.adapter.out;

import com.triple.homework.user.application.port.out.UserPort;
import com.triple.homework.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class UserRepository implements UserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(String userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
