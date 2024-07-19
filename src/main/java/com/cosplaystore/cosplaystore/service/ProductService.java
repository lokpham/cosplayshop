package com.cosplaystore.cosplaystore.service;

import java.util.List;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.model.Product;

public interface ProductService {
    List<Product> getAllProduct(int start, int size);

    Product updateProduct(int id, ProductRequest p);

    void disableProduct(int id);

    Product getProduct(int id);

    Product addProduct(ProductRequest productRequest);

}
