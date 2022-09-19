package com.thoughtworks.capability.service;

import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.repository.ProductRepository;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
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

    @Test
    public void shouldReturnTwoProductsInShoppingCartWhenHasData() {
        // given
        Product p1 = Product.builder().id(1L).name("p1").price(BigDecimal.valueOf(10)).build();
        Product p2 = Product.builder().id(2L).name("p2").price(BigDecimal.valueOf(20)).build();
        Mockito.when(productRepository.findAll()).thenReturn(asList(p1, p2));

        // when
        ShoppingCartResponse response = service.showShoppingCart();

        // then
        assertNotNull(response);
        assertNotNull(response.getProducts());
        assertEquals(2, response.getProducts().size());
        assertEquals(BigDecimal.valueOf(30), response.getTotalPrice());
    }
}
