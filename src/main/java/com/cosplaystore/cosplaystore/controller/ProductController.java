package com.cosplaystore.cosplaystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.dto.response.ResponseObject;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.mapper.ProductMapper;
import com.cosplaystore.cosplaystore.model.Product;
import com.cosplaystore.cosplaystore.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/product")

public class ProductController {
        @Autowired
        ProductService productService;
        @Autowired
        ProductMapper productMapper;
        private int pageSize = 10;

        @GetMapping("/all")
        public ResponseEntity<ResponseObject> getMethodName(@RequestParam int page) {
                List<Product> products = productService.getAllProduct(page, pageSize);

                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Get All Product Secucess!")
                                                .data(productMapper.toProductResponse(products.get(0)))
                                                .build());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ResponseObject> getProduct(@PathVariable int id) {
                Product product = productService.getProduct(id);

                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Get All Product Secucess!")
                                                .data(productMapper.toProductResponse(product))
                                                .build());
        }

        @PostMapping("/add")
        public ResponseEntity<ResponseObject> postMethodName(@RequestBody ProductRequest productRequest) {
                // TODO: process POST request
                Product product = productService.addProduct(productRequest);
                if (product != null) {
                        return ResponseEntity.ok()
                                        .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                        .message("Add a product successful!")
                                                        .data(productMapper.toProductResponse(product))
                                                        .build());
                } else {
                        throw new GeneralException(Message.PRODUCT_ADD_FAILED);
                }

        }

}