package com.cosplaystore.cosplaystore.dto.request;

import lombok.Getter;

@Getter
public class RateRequest {
    private int value;
    private int user_id;
    private int product_id;
}
