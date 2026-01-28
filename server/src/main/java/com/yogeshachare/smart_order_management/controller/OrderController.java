package com.yogeshachare.smart_order_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yogeshachare.smart_order_management.entity.OrderEntity;
import com.yogeshachare.smart_order_management.entity.User;
import com.yogeshachare.smart_order_management.service.OrderService;
import com.yogeshachare.smart_order_management.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder() {
        User user = userService.getLoggedInUser();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        OrderEntity order = orderService.createOrder(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(order);
    }

    @PostMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<OrderEntity> addItemToOrder(@PathVariable Long orderId,
            @PathVariable Long itemId,
            @RequestParam Integer quantity) {
        OrderEntity order = orderService.addItemToOrder(orderId, itemId, quantity);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order cancelled successfully");
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<String> completeOrder(@PathVariable Long orderId) {
        orderService.completeOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body("Order completed successfully");
    }
}
