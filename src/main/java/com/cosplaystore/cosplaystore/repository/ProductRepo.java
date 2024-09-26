package com.cosplaystore.cosplaystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosplaystore.cosplaystore.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product_variant, variant where product_id = :id and variant_id = variant.id  order by priority asc", nativeQuery = true)
    List<Object[]> getProductVariant(@Param("id") int id);

    @Query(value = "SELECT COALESCE(CAST(FLOOR(AVG(value)) AS INT), 0) FROM product_rate WHERE product_id = :id", nativeQuery = true)
    int getAverageRateRoundedDown(@Param("id") int id);

    @Query(value = "SELECT count(*) FROM product_rate where product_id = :id", nativeQuery = true)
    int getRateCount(@Param("id") int id);

    @Query(value = "SELECT DISTINCT  variant_value.value FROM `product_item_variant`,variant_value,product_item WHERE product_item_variant.variant_id = :variant_id and product_item_variant.value = variant_value.id and product_item_variant.product_item_id = product_item.id and product_item.product_id = :product_id", nativeQuery = true)
    List<Object[]> getProductVariantValue(@Param("variant_id") int variant_id, @Param("product_id") int product_id);
}
