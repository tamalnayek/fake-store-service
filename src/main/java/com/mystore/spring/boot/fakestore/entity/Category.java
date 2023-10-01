package com.mystore.spring.boot.fakestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
   // @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    //private List<Product> products = new ArrayList<>();

}