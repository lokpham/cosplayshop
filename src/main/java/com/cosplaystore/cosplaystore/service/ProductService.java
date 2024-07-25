package com.cosplaystore.cosplaystore.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.model.Product;

public interface ProductService {
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    List<Product> getAllProduct(int start, int size);

    Product updateProduct(int id, ProductRequest p);

    void disableProduct(int id);

    void unDisableProduct(int id);

    Product getProduct(int id);

    Product addProduct(ProductRequest productRequest);

}
