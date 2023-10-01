package com.mystore.spring.boot.fakestore.service;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;
import com.mystore.spring.boot.fakestore.entity.Category;
import com.mystore.spring.boot.fakestore.entity.Product;
import com.mystore.spring.boot.fakestore.mapper.ProductMapper;
import com.mystore.spring.boot.fakestore.repository.CategoryRepository;
import com.mystore.spring.boot.fakestore.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
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
        //Product product = ProductMapper.MAPPER.mapToProduct(productDTO);
        Product result = productRepository.save(productMapper.mapToProduct(productDTO));
        return ProductMapper.MAPPER.mapToProductDTO(result);
    }

    public ProductDTO getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::mapToProductDTO).orElse(null);
        //throw new ProductNotFound();
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapToProductDTO).collect(Collectors.toList());
    }

    public String deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return "Product deleted ! ID = "+id;
        }
        return null;
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        Optional<Product> product = productRepository.findById(productDTO.getId());
        if(product.isPresent()){
            productRepository.saveAndFlush(productMapper.mapToProduct(productDTO));
        }
        return null;
    }

    public ProductDTO patchProduct(ProductDTO productDTO) {
       Optional<Product> target = productRepository.findById(productDTO.getId());
       if(target.isPresent()){
           Product product = ProductMapper.MAPPER.mapToPatchUpdateProduct(productMapper.mapToProduct(productDTO), target.get());
           productRepository.saveAndFlush(product);
       }
        Optional<Product> result = productRepository.findById(productDTO.getId());
        return result.map(productMapper::mapToProductDTO).orElse(null);
    }

    public List<ProductDTO> getProductsByCategory(Long id){
        Category  category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return null;
        }
        List<Product> products = productRepository.findByCategory(category);
        return products.stream().map(productMapper::mapToProductDTO).collect(Collectors.toList());
    }
}
