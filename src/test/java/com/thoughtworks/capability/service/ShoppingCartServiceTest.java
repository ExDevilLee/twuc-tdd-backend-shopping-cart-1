package com.thoughtworks.capability.service;

import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ShoppingCartServiceTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ShoppingCartService service = new ShoppingCartService(productRepository);

    @Test
    public void shouldReturnEmptyProductsWhenHasNoData() {
        // given

        // when
        ShoppingCartResponse response = service.showShoppingCart();

        // then
        assertNotNull(response);
        assertNotNull(response.getProducts());
        assertEquals(0, response.getProducts().size());
        assertEquals(BigDecimal.ZERO, response.getTotalPrice());
    }
}
