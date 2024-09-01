package com.cosplaystore.cosplaystore.service;

import java.util.List;

import com.cosplaystore.cosplaystore.dto.response.ProductItemResponse;
import com.cosplaystore.cosplaystore.model.ProductItem;

public interface ProductItemService {
    public List<ProductItemResponse> getProductItemsViaProductId(int id);
}
