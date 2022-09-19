package com.thoughtworks.capability.repository;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

class ProductRepositoryTest extends WebApplicationTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void shouldReturnEmptyWhenHasNoData() {
        // given

        // when
        List<Product> products = repository.findAll();

        // then
        Assertions.assertNotNull(products);
        Assertions.assertEquals(0, products.size());
    }

    @Test
    @Sql("/sql/insert_two_products.sql")
    public void shouldReturnTwoProductsWhenHasData() {
        // given

        // when
        List<Product> products = repository.findAll();

        // then
        Assertions.assertNotNull(products);
        Assertions.assertEquals(2, products.size());
    }
}
