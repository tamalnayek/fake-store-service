package com.mystore.spring.boot.fakestore.service;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO product);
    public ProductDTO getProductById(Long id);
    public List<ProductDTO> getProductsByCategory(Long id);
    public List<ProductDTO> getAllProducts();
    public String deleteProduct(Long id);
    public ProductDTO updateProduct(ProductDTO product);
    public ProductDTO patchProduct(ProductDTO product) ;

}
