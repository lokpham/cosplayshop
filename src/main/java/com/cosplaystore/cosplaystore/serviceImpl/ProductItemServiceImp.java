package com.cosplaystore.cosplaystore.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.dto.response.ProductItemResponse;
import com.cosplaystore.cosplaystore.mapper.ProductMapper;
import com.cosplaystore.cosplaystore.model.ProductItem;
import com.cosplaystore.cosplaystore.model.ProductItemVariant;
import com.cosplaystore.cosplaystore.repository.ProductItemRepo;
import com.cosplaystore.cosplaystore.service.ProductItemService;
import com.cosplaystore.cosplaystore.service.ProductService;

@Service
public class ProductItemServiceImp implements ProductItemService {

    @Autowired
    ProductItemRepo productItemRepo;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductItemResponse> getProductItemsViaProductId(int id) {
        List<ProductItem> productItems = productItemRepo.getProductItemsViaProductId(id);
        List<ProductItemResponse> productItemResponses = new ArrayList<>();
        for (ProductItem productItem : productItems) {
            List<String> variants = new ArrayList<>();
            for (ProductItemVariant productItemVariant : productItem.getProductItemVariants()) {
                variants.add(productItemVariant.getVariantValue().getValue());
            }
            ProductItemResponse productItemResponse = productMapper.toProductItemResponse(productItem);
            productItemResponse.setVariants(variants);
            productItemResponses.add(productItemResponse);

        }
        return productItemResponses;
    }

}
