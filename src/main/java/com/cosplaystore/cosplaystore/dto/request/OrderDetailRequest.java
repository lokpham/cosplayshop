package com.cosplaystore.cosplaystore.dto.request;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class OrderDetailRequest {
    private BigDecimal unit_price;
    private int quantity;
    private int product_id;
}
