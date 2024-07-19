package com.cosplaystore.cosplaystore.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cosplaystore.cosplaystore.model.Catetory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private int stock;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private int like_number;
    private Catetory catetory;
    private Boolean disable;

}
