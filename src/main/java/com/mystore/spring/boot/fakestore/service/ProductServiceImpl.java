package com.mystore.spring.boot.fakestore.service;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;
import com.mystore.spring.boot.fakestore.entity.Category;
import com.mystore.spring.boot.fakestore.entity.Product;
import com.mystore.spring.boot.fakestore.exception.NoRecordFoundException;
import com.mystore.spring.boot.fakestore.mapper.ProductMapper;
import com.mystore.spring.boot.fakestore.repository.CategoryRepository;
import com.mystore.spring.boot.fakestore.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Primary
@Service("dbProductService")
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

   private final ProductMapper productMapper = ProductMapper.MAPPER;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        Product result = productRepository.save(productMapper.mapToProduct(productDTO));
        return productMapper.mapToProductDTO(result);
    }

    public ProductDTO getProductById(Long id) throws NoRecordFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new NoRecordFoundException("No record exist against this Product ID : "+id);
        }
        return productMapper.mapToProductDTO(product.get());
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapToProductDTO).collect(Collectors.toList());
    }

    public String deleteProduct(Long id) throws NoRecordFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return "Product deleted of this ID = "+id;
        }
        throw new NoRecordFoundException("No record exist to remove !!!");
    }

    public ProductDTO updateProduct(ProductDTO productDTO) throws NoRecordFoundException {
        Optional<Product> product = productRepository.findById(productDTO.getId());
        if(product.isPresent()){
            productRepository.saveAndFlush(productMapper.mapToProduct(productDTO));
            return productDTO;
        }
        throw new NoRecordFoundException("No record exist to update !!!");
    }

    public ProductDTO patchProduct(ProductDTO productDTO) throws NoRecordFoundException {
       Optional<Product> target = productRepository.findById(productDTO.getId());
       if(target.isPresent()){
           Product product = productMapper.mapToPatchUpdateProduct(productMapper.mapToProduct(productDTO), target.get());
           productRepository.saveAndFlush(product);
           return productMapper.mapToProductDTO(product);
       }
        throw new NoRecordFoundException("No record exist to update !!!");
    }

    @Override
    public List<ProductDTO> getProductsWithLimit(Pageable limit) {
        List<Product> allWithLimit = productRepository.findAllWithLimit(limit);
        return allWithLimit.stream().map(productMapper::mapToProductDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(Long id) throws NoRecordFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new NoRecordFoundException("No such category exist !!!");
        }
        List<Product> products = productRepository.findByCategory(category.get());
        return products.stream().map(productMapper::mapToProductDTO).collect(Collectors.toList());
    }
}
