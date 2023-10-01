package com.mystore.spring.boot.fakestore.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.mystore.spring.boot.fakestore.entity.Product}
 */
@Value
public class ProductDTO implements Serializable {
    Long id;
    String title;
    String description;
    String image;
    Long price;
    String currency;
    CategoryDTO category;
}