package com.cosplaystore.cosplaystore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cosplaystore.cosplaystore.dto.request.OrderRequest;
import com.cosplaystore.cosplaystore.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    Order toOrder(OrderRequest OrderRequest);
}
