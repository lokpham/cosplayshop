package com.cosplaystore.cosplaystore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosplaystore.cosplaystore.model.ProductRate;

public interface ProductRateRepo extends JpaRepository<ProductRate, Integer> {
    @Query("SELECT pr FROM ProductRate pr WHERE pr.user.id = :userId AND pr.product.id = :productId")
    Optional<ProductRate> findByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);
}
