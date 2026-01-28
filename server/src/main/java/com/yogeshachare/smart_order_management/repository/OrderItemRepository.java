package com.yogeshachare.smart_order_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yogeshachare.smart_order_management.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByOrderIdAndItemId(Long orderId, Long itemId);
}
