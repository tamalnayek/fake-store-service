package com.mystore.spring.boot.fakestore.service;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;
import com.mystore.spring.boot.fakestore.exception.NoRecordFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO product);
    public ProductDTO getProductById(Long id) throws NoRecordFoundException;
    public List<ProductDTO> getProductsByCategory(Long id) throws NoRecordFoundException;
    public List<ProductDTO> getAllProducts();
    public String deleteProduct(Long id) throws NoRecordFoundException;
    public ProductDTO updateProduct(ProductDTO product) throws NoRecordFoundException;
    public ProductDTO patchProduct(ProductDTO product) throws NoRecordFoundException;
    public List<ProductDTO> getProductsWithLimit(Pageable limit);

}
