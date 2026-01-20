package com.yogeshachare.smart_order_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yogeshachare.smart_order_management.dto.ItemRequestDto;
import com.yogeshachare.smart_order_management.dto.ItemResponseDto;
import com.yogeshachare.smart_order_management.entity.Item;
import com.yogeshachare.smart_order_management.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponseDto createItem(ItemRequestDto itemDto) {
        if (itemDto == null) {
            throw new IllegalArgumentException("Item data cannot be null");
        }

        Item item = new Item();
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setImageUrl(itemDto.getImageUrl());

        Item savedItem = itemRepository.save(item);
        return new ItemResponseDto(savedItem.getId(), savedItem.getName(), savedItem.getDescription(),
                savedItem.getPrice(), savedItem.getImageUrl());
    }

    public List<ItemResponseDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item -> new ItemResponseDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(),
                        item.getImageUrl()))
                .toList();
    }
}
