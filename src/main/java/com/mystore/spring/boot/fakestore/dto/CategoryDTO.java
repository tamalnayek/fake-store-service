package com.mystore.spring.boot.fakestore.dto;

import com.mystore.spring.boot.fakestore.entity.Product;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.mystore.spring.boot.fakestore.entity.Category}
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    Long id;
    String name;
    String description;
}