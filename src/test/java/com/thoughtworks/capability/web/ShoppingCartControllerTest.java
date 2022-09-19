package com.thoughtworks.capability.web;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShoppingCartControllerTest extends WebApplicationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    public ShoppingCartService service;

    @Test
    public void shouldReturnEmptyShoppingCartWhenHasNoProduct() throws Exception {
        // given
        List<Product> products = Collections.emptyList();
        BigDecimal totalPrice = BigDecimal.ZERO;
        ShoppingCartResponse response = new ShoppingCartResponse(products, totalPrice);
        when(service.showShoppingCart()).thenReturn(response);

        // when
        mvc.perform(MockMvcRequestBuilders
                .get("/shoppingCart")
                .accept(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").exists())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products").isEmpty())
                .andExpect(jsonPath("$.totalPrice").exists())
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.ZERO));
    }

    @Test
    public void shouldReturnTwoProductsInShoppingCartWhenHasData() throws Exception {
        // given
        Product p1 = Product.builder().id(1L).name("p1").price(BigDecimal.valueOf(10)).build();
        Product p2 = Product.builder().id(2L).name("p2").price(BigDecimal.valueOf(20)).build();
        ShoppingCartResponse response = new ShoppingCartResponse(asList(p1, p2), BigDecimal.valueOf(30));
        when(service.showShoppingCart()).thenReturn(response);

        // when
        mvc.perform(MockMvcRequestBuilders
                .get("/shoppingCart")
                .accept(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").exists())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products[0].name").value("p1"))
                .andExpect(jsonPath("$.totalPrice").exists())
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.valueOf(30)));
    }
}
