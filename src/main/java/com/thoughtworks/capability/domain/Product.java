package com.thoughtworks.capability.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;
}
