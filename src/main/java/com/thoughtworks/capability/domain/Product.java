package com.thoughtworks.capability.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
