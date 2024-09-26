package com.cosplaystore.cosplaystore.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.cosplaystore.cosplaystore.model.Catetory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private int id;
    private String name;
    private String image;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private int price;
    private int discount;
    private Boolean disable;
    private String tags;
    private RateDTO rate;
}
