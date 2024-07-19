package com.cosplaystore.cosplaystore.service;

import com.cosplaystore.cosplaystore.dto.request.OrderRequest;
import com.cosplaystore.cosplaystore.model.Order;

public interface OrderService {

    public Order getOrder(int id);

    public Order createOrder(OrderRequest orderRequest);
}
