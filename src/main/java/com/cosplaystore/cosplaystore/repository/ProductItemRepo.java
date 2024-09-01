package com.cosplaystore.cosplaystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosplaystore.cosplaystore.model.ProductItem;

public interface ProductItemRepo extends JpaRepository<ProductItem, Integer> {
    @Query(value = "SELECT * FROM product_item WHERE product_item.product_id = :id", nativeQuery = true)
    List<ProductItem> getProductItemsViaProductId(@Param("id") int id);
}
