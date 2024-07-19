package com.cosplaystore.cosplaystore.dto.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class OrderRequest {
    private BigDecimal price;
    private Date order_date;

    private int userid;

    private List<OrderDetailRequest> orderDetailRequests;
}
