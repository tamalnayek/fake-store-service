package com.mystore.spring.boot.fakestore.repository;

import com.mystore.spring.boot.fakestore.entity.Category;
import com.mystore.spring.boot.fakestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from  Product p where p.category =:category")
    List<Product> findByCategory(@Param("category") Category category);

    @Query("select p from Product p ")
    List<Product> findAllWithLimit(@Param("limit") int limit);

    @Query("select p from Product p ")
    List<Product> findAllWithLimit(Pageable pageable);

    // Fetch all products with pagination
    Page<Product> findAll(Pageable pageable);
}