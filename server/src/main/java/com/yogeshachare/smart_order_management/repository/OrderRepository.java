package com.yogeshachare.smart_order_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yogeshachare.smart_order_management.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
