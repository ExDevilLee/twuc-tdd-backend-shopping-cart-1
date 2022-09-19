package com.thoughtworks.capability.web.dto;

import com.thoughtworks.capability.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ShoppingCartResponse {
    private List<Product> products;
    private BigDecimal totalPrice;
}
