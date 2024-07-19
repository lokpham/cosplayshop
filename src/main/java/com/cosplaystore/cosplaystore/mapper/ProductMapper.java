package com.cosplaystore.cosplaystore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.dto.response.ProductResponse;
import com.cosplaystore.cosplaystore.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "catetory", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    Product toProduct(ProductRequest productRequest);

    List<ProductResponse> toListProductResponse(List<Product> list);

}
