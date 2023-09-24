package com.mystore.spring.boot.fakestore.model;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
public class Product extends BasePojo{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}
