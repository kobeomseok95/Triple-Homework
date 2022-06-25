package com.triple.homework.user.adapter.out;

import com.triple.homework.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserJpaRepository extends JpaRepository<User, String> {
}
