package com.yogeshachare.smart_order_management.mapper;

import com.yogeshachare.smart_order_management.dto.OrderItemDto;
import com.yogeshachare.smart_order_management.entity.OrderItem;

import com.yogeshachare.smart_order_management.dto.OrderResponseDto;
import com.yogeshachare.smart_order_management.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderResponseDto toOrderResponseDto(OrderEntity orderEntity){
      OrderResponseDto orderResponseDto = new OrderResponseDto();
      orderResponseDto.setId(orderEntity.getId());
      orderResponseDto.setOrderNumber(orderEntity.getOrderNumber());
      orderResponseDto.setStatus(orderEntity.getStatus());
      List<OrderItemDto> items = orderEntity.getOrderItems().stream()
              .map(i -> toOderItemDTo(i))
              .toList();
      orderResponseDto.setItems(items);
      return orderResponseDto;
    }

    private OrderItemDto toOderItemDTo(OrderItem item) {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(item.getId());
        dto.setItemId(item.getItem().getId());
        dto.setItemName(item.getItem().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }
}