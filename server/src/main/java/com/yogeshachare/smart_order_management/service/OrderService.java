package com.yogeshachare.smart_order_management.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yogeshachare.smart_order_management.dto.OrderStatus;
import com.yogeshachare.smart_order_management.entity.Item;
import com.yogeshachare.smart_order_management.entity.OrderEntity;
import com.yogeshachare.smart_order_management.entity.OrderItem;
import com.yogeshachare.smart_order_management.entity.User;
import com.yogeshachare.smart_order_management.repository.ItemRepository;
import com.yogeshachare.smart_order_management.repository.OrderItemRepository;
import com.yogeshachare.smart_order_management.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderEntity createOrder(User user) {
        OrderEntity order = OrderEntity.builder()
                .orderNumber(UUID.randomUUID().toString())
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.ZERO)
                .user(user)
                .build();
        return orderRepository.save(order);
    }

    @Transactional
    public OrderEntity addItemToOrder(Long orderId, Long itemId, Integer quantity) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getStatus() == OrderStatus.CANCELLED ||
                order.getStatus() == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("Cannot add items to a cancelled or completed order");
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        OrderItem orderItem = orderItemRepository
                .findByOrderIdAndItemId(orderId, itemId);

        if (orderItem != null) {
            orderItem.setQuantity(orderItem.getQuantity() + quantity);
        } else {
            orderItem = OrderItem.builder()
                    .order(order)
                    .item(item)
                    .quantity(quantity)
                    .price(BigDecimal.valueOf(item.getPrice()))
                    .build();
        }
        orderItemRepository.save(orderItem);
        recalculateTotalAmount(order);
        return orderRepository.save(order);
    }

    private void recalculateTotalAmount(OrderEntity order) {
        BigDecimal totalAmount = order.getOrderItems().stream()
                .map(oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);
    }

    public void cancelOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalArgumentException("Order is already cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    public void completeOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("Order is already completed");
        }

        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
    }
}
