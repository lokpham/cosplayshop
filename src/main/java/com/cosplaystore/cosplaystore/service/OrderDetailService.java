package com.cosplaystore.cosplaystore.service;

import com.cosplaystore.cosplaystore.dto.request.OrderDetailRequest;
import com.cosplaystore.cosplaystore.model.OrderDetail;

public interface OrderDetailService {

    OrderDetail addOrderDetail(OrderDetailRequest orderDetailRequest);
}
