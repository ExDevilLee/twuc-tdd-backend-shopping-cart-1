package com.thoughtworks.capability.web.dto;

import com.thoughtworks.capability.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class ShoppingCartResponse {
    private List<Product> products;
    private BigDecimal totalPrice;
}
