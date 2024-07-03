package com.vorobey.userservice.repository;

import com.vorobey.userservice.entity.cart.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
