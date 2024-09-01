package com.cosplaystore.cosplaystore.dto.response;

import java.util.List;
import java.util.Map;

import com.cosplaystore.cosplaystore.model.Product;
import com.cosplaystore.cosplaystore.model.ProductItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductFullInforResponse {
    @JsonProperty(value = "product")
    private ProductResponse productResponse;
    private List<ProductItemResponse> product_items;
    private List<VariantDTO> variants;
}
