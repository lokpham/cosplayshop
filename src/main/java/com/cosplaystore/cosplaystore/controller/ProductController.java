package com.cosplaystore.cosplaystore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosplaystore.cosplaystore.dto.request.ProductRequest;
import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.dto.response.ProductFullInforResponse;
import com.cosplaystore.cosplaystore.dto.response.ProductResponse;
import com.cosplaystore.cosplaystore.dto.response.ResponseObject;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.model.ProductItem;
import com.cosplaystore.cosplaystore.service.ProductItemService;
import com.cosplaystore.cosplaystore.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Validated
@RequestMapping(value = "/product")

public class ProductController {
        @Autowired
        ProductService productService;
        private int pageSize = 10;

        @GetMapping("/all")
        public ResponseEntity<ResponseObject> getMethodName(@RequestParam int page) {
                List<ProductResponse> products = productService.getAllProduct(page, pageSize);

                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Get All Product Secucess!")
                                                .data(products)
                                                .build());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ResponseObject> getProduct(@Min(value = 0) @PathVariable int id) {
                ProductResponse product = productService.getProduct(id);

                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Get All Product Secucess!")
                                                .data(product)
                                                .build());
        }

        @PutMapping("/disable/{id}")
        public ResponseEntity<ResponseObject> putMethodName(@PathVariable int id) {
                productService.disableProduct(id);
                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Disable Secucess!")
                                                .data(null)
                                                .build());
        }

        @PutMapping("/undisable/{id}")
        public ResponseEntity<ResponseObject> undisable(@PathVariable int id) {
                productService.unDisableProduct(id);
                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Undisable Secucess!")
                                                .data(null)
                                                .build());
        }

        @PostMapping("/add")
        public ResponseEntity<ResponseObject> postMethodName(@Valid @RequestBody ProductRequest productRequest) {
                ProductResponse product = productService.addProduct(productRequest);
                if (product != null) {
                        return ResponseEntity.ok()
                                        .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                        .message("Add a product successful!")
                                                        .data(product)
                                                        .build());
                } else {
                        throw new GeneralException(Message.PRODUCT_ADD_FAILED);
                }

        }

        @PutMapping("update/{id}")
        public ResponseEntity<ResponseObject> update(@PathVariable int id,
                        @Valid @RequestBody ProductRequest productRequest) {
                ProductResponse product = productService.updateProduct(id, productRequest);
                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Add a product successful!")
                                                .data(product)
                                                .build());

        }

        @GetMapping("details/{id}")
        public ResponseEntity<ResponseObject> getDetail(@PathVariable int id) {
                ProductFullInforResponse productFullInforResponse = productService.getFullInfor(id);
                return ResponseEntity.ok()
                                .body(ResponseObject.builder().status_code(HttpStatus.OK.value())
                                                .message("Add a product successful!")
                                                .data(productFullInforResponse)
                                                .build());

        }

}
