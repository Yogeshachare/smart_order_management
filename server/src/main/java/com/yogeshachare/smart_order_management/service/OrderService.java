package com.yogeshachare.smart_order_management.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yogeshachare.smart_order_management.dto.OrderStatus;
import com.yogeshachare.smart_order_management.entity.OrderEntity;
import com.yogeshachare.smart_order_management.entity.User;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Transactional
    public OrderEntity createOrder(User user) {
        OrderEntity order = OrderEntity.builder()
                .orderNumber(UUID.randomUUID().toString())
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.ZERO)
                .user(user)
                .build();
        return order;
    }
}
