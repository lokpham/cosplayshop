package com.cosplaystore.cosplaystore.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.dto.response.Message;
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
    public List<Product> getAllProduct(int start, int size) {
        Pageable page = PageRequest.of((start - 1) * size, size);
        Page<Product> products = productRepo.findAll(page);
        if (products.hasContent()) {
            return products.getContent();
        } else {
            return null;
        }
    }

    @Override
    public Product updateProduct(int id, ProductRequest p) {
        Product product = getProduct(id);
        product.setDescription(p.getDescription());
        product.setName(p.getName());
        product.setPrice(p.getPrice());
        product.setStock(p.getStock());
        product.setDisable(p.getDisable());
        Catetory catetory = catetoryService.getCatetory(p.getCatetory_id());
        product.setCatetory(catetory);
        return productRepo.save(product);

    }

    @Override
    public void disableProduct(int id) {
        Product product = getProduct(id);
        product.setDisable(true);
        productRepo.save(product);
    }

    @Override
    public Product getProduct(int id) {
        Optional<Product> product = productRepo.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new GeneralException(Message.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Catetory catetory = catetoryService.getCatetory(productRequest.getCatetory_id());
        product.setCatetory(catetory);
        return productRepo.save(product);

    }

}
