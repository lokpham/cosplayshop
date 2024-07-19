package com.cosplaystore.cosplaystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosplaystore.cosplaystore.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
