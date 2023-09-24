package com.mystore.spring.boot.fakestore.service;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreSevice {
    @Value("${fakestore.api.url}")
    private String URL;

    @Value("${fakestore.api.url.products}")
    private String PRODUCTS;

    private final RestTemplate restTemplate;

    public FakeStoreSevice(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public ProductDTO createProduct(ProductDTO product){
        ResponseEntity<ProductDTO> productDTOResponseEntity =
                restTemplate.postForEntity(URL.concat(PRODUCTS), product, ProductDTO.class);
        return productDTOResponseEntity.getBody();
    }

    public ProductDTO getProductById(Long id){
        ResponseEntity<ProductDTO> forEntity = restTemplate.getForEntity(URL.concat(PRODUCTS).concat("/{id}"), ProductDTO.class, id);
        return Objects.requireNonNull(forEntity).getBody();
    }

    public List<ProductDTO> getAllProducts(){
        ResponseEntity<ProductDTO[]> forEntity = restTemplate.getForEntity(URL.concat(PRODUCTS), ProductDTO[].class);
        return Arrays.asList(Objects.requireNonNull(forEntity.getBody()));
    }

    public ProductDTO deleteProduct(Long id){

        ResponseEntity<ProductDTO> response = restTemplate.execute(URL.concat(PRODUCTS).concat("/{id}"), HttpMethod.DELETE,
                restTemplate.acceptHeaderRequestCallback(ProductDTO.class),
                restTemplate.responseEntityExtractor(ProductDTO.class), id);
        return Objects.requireNonNull(response).getBody();
    }

    public ProductDTO updateProduct(ProductDTO product){
        return getProductDTO(product, product.getId());
    }

    public ProductDTO patchProduct(ProductDTO product) {
        ProductDTO dbObject = this.getProductById(product.getId());
        dbObject.setCategory(product.getCategory()==null? dbObject.getCategory() : product.getCategory());
        dbObject.setTitle(product.getTitle()==null? dbObject.getTitle() : product.getTitle());
        dbObject.setDescription(product.getDescription()==null? dbObject.getDescription() : product.getDescription());
        dbObject.setImage(product.getImage()==null? dbObject.getImage() : product.getImage());
        dbObject.setPrice(product.getPrice() > 0? product.getPrice() : dbObject.getPrice());

        return getProductDTO(dbObject, product.getId());
    }

    private ProductDTO getProductDTO(ProductDTO product, Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(product, headers);
        ResponseEntity<ProductDTO> response = restTemplate.exchange(URL.concat(PRODUCTS).concat("/{id}"), HttpMethod.PUT,
                requestEntity, ProductDTO.class, id);
        return response.getBody();
    }
}
