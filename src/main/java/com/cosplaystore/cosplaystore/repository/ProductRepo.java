package com.cosplaystore.cosplaystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosplaystore.cosplaystore.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
