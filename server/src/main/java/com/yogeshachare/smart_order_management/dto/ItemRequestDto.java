package com.yogeshachare.smart_order_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive
    private Double price;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;
}
