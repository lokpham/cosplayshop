package com.cosplaystore.cosplaystore.dto.request;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class ProductRequest {

    private String name;
    private BigDecimal price;
    private int stock;
    private String description;
    private int catetory_id;
    private Boolean disable;

}
