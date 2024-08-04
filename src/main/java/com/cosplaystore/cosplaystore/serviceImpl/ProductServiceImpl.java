package com.cosplaystore.cosplaystore.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.dto.response.ProductResponse;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.mapper.ProductMapper;
import com.cosplaystore.cosplaystore.model.Catetory;
import com.cosplaystore.cosplaystore.model.Product;
import com.cosplaystore.cosplaystore.repository.ProductRepo;
import com.cosplaystore.cosplaystore.service.CatetoryService;
import com.cosplaystore.cosplaystore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CatetoryService catetoryService;
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProduct(int start, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println(authentication.getAuthorities());
        }
        Pageable page = PageRequest.of((start - 1) * size, size);
        Page<Product> products = productRepo.findAll(page);
        if (products.hasContent()) {
            return productMapper.toListProductResponse(products.getContent());
        } else {
            return null;
        }
    }

    @Override
    public ProductResponse updateProduct(int id, ProductRequest p) {
        Product product = getProductById(id);
        product.setDescription(p.getDescription());
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        product.setStock(p.getStock());
        product.setLike_number(p.getLike_number());
        Catetory catetory = catetoryService.getCatetoryById(p.getCatetory_id());
        product.setCatetory(catetory);
        return productMapper.toProductResponse(productRepo.save(product));

    }

    @Override
    public void disableProduct(int id) {
        Product product = getProductById(id);
        product.setDisable(true);
        productRepo.save(product);
    }

    public Product getProductById(int id) {
        Optional<Product> product = productRepo.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new GeneralException(Message.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Catetory catetory = catetoryService.getCatetoryById(productRequest.getCatetory_id());
        product.setCatetory(catetory);
        return productMapper.toProductResponse(productRepo.save(product));

    }

    @Override
    public void unDisableProduct(int id) {
        Product product = getProductById(id);
        product.setDisable(false);
        productRepo.save(product);
    }

    @Override
    public ProductResponse getProduct(int id) {
        Product product = getProductById(id);
        return productMapper.toProductResponse(product);
    }

}
