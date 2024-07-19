package com.cosplaystore.cosplaystore.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cosplaystore.cosplaystore.dto.request.OrderDetailRequest;
import com.cosplaystore.cosplaystore.dto.request.OrderRequest;
import com.cosplaystore.cosplaystore.mapper.OrderMapper;
import com.cosplaystore.cosplaystore.model.Order;
import com.cosplaystore.cosplaystore.model.OrderDetail;
import com.cosplaystore.cosplaystore.model.User;
import com.cosplaystore.cosplaystore.repository.OrderRepo;
import com.cosplaystore.cosplaystore.service.OrderService;
import com.cosplaystore.cosplaystore.service.ProductService;
import com.cosplaystore.cosplaystore.service.UserService;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductService productService;

    @Override
    public Order getOrder(int id) {
        return null;
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);

        User user = userService.getUser(orderRequest.getUserid());
        order.setUser(user);

        for (OrderDetailRequest orderDetailRequest : orderRequest.getOrderDetailRequests()) {
            order.getOrderDetails()
                    .add(OrderDetail
                            .builder()
                            .quantity(orderDetailRequest.getQuantity())
                            .unit_price(orderDetailRequest.getUnit_price())
                            .order(order)
                            .product(productService.getProduct(orderDetailRequest.getProduct_id()))
                            .build());
        }
        return orderRepo.save(order);

    }

}
