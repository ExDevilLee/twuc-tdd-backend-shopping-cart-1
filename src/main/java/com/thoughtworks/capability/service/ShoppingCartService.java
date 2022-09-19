package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class ShoppingCartService {
    private final ProductRepository productRepository;

    public ShoppingCartResponse showShoppingCart() {
        List<Product> products = productRepository.findAll();

        BigDecimal totalPrice = BigDecimal.ZERO;

        return ShoppingCartResponse.builder()
                .products(products)
                .totalPrice(totalPrice)
                .build();
    }
}
