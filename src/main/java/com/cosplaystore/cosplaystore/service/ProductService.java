package com.cosplaystore.cosplaystore.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.dto.response.ProductResponse;
import com.cosplaystore.cosplaystore.model.Product;

public interface ProductService {
    // @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    List<ProductResponse> getAllProduct(int start, int size);

    ProductResponse updateProduct(int id, ProductRequest p);

    void disableProduct(int id);

    void unDisableProduct(int id);

    ProductResponse getProduct(int id);

    ProductResponse addProduct(ProductRequest productRequest);

    Product getProductById(int id);
}
