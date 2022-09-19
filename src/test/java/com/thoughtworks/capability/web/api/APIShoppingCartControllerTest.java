package com.thoughtworks.capability.web.api;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Product;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class APIShoppingCartControllerTest extends WebApplicationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @Sql("/sql/insert_two_products.sql")
    public void shouldReturnTwoProductsInShoppingCartWhenHasData() throws Exception {
        // given

        // when
        mvc.perform(MockMvcRequestBuilders
                        .get("/shoppingCart")
                        .accept(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products").exists())
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products[0].name").value("product1"))
                .andExpect(jsonPath("$.totalPrice").exists())
                .andExpect(jsonPath("$.totalPrice").value(BigDecimal.valueOf(30.0)));
    }
}
