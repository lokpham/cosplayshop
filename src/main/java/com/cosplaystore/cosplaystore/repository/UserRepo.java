package com.cosplaystore.cosplaystore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosplaystore.cosplaystore.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
