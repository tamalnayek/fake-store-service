package com.mystore.spring.boot.fakestore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {

    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
