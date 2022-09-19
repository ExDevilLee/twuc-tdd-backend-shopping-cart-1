package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ProductRepository productRepository;

    public ShoppingCartResponse showShoppingCart() {
        return ShoppingCartResponse.builder()
                .products(productRepository.findAll())
                .totalPrice(calcTotalPrice(productRepository.findAll()))
                .build();
    }

    private static BigDecimal calcTotalPrice(List<Product> products) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : products) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }
}
