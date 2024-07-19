package com.cosplaystore.cosplaystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosplaystore.cosplaystore.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
