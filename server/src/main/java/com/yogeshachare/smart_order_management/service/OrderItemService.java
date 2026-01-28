package com.yogeshachare.smart_order_management.service;

import org.springframework.stereotype.Service;

import com.yogeshachare.smart_order_management.dto.OrderStatus;
import com.yogeshachare.smart_order_management.entity.Item;
import com.yogeshachare.smart_order_management.entity.OrderEntity;
import com.yogeshachare.smart_order_management.repository.ItemRepository;
import com.yogeshachare.smart_order_management.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {

        private final OrderRepository orderRepository;
        private final ItemRepository itemRepository;

    @Transactional
    public OrderEntity addOrderItem(Long orderId, Long itemId, Integer quantity) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        
        if (order.getStatus() == OrderStatus.CANCELLED ||
                order.getStatus() == OrderStatus.COMPLETED) {
        throw new IllegalArgumentException("Cannot add items to a cancelled or completed order");
        }   

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        OrderItem orderItem =  
    }
}
