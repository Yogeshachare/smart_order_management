package com.yogeshachare.smart_order_management.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String orderNumber;
    private OrderStatus status;
    private List<OrderItemDto> items;
}
