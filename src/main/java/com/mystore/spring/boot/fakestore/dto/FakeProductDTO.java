package com.mystore.spring.boot.fakestore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductDTO {

    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
