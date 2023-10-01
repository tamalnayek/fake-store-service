package com.mystore.spring.boot.fakestore.entity;

import jakarta.persistence.*;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
