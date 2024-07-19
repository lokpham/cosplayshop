package com.cosplaystore.cosplaystore.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRequest {

    @NotBlank
    @NotNull
    private String name;

    @Min(value = 1)
    private BigDecimal price;
    @Min(value = 0)
    private int stock;

    private String description;
    @NotNull
    @Min(value = 1)
    private int catetory_id;

    @Min(value = 0)
    private int like_number;

    private Boolean disable;
}
