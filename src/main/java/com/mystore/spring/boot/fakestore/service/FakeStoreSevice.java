package com.mystore.spring.boot.fakestore.service;

import com.mystore.spring.boot.fakestore.dto.FakeProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    public FakeProductDTO createProduct(FakeProductDTO product){
        ResponseEntity<FakeProductDTO> productDTOResponseEntity =
                restTemplate.postForEntity(URL.concat(PRODUCTS), product, FakeProductDTO.class);
        return productDTOResponseEntity.getBody();
    }

    public FakeProductDTO getProductById(Long id){
        ResponseEntity<FakeProductDTO> forEntity = restTemplate.getForEntity(URL.concat(PRODUCTS).concat("/{id}"), FakeProductDTO.class, id);
        return Objects.requireNonNull(forEntity).getBody();
    }

    public List<FakeProductDTO> getAllProducts(){
        ResponseEntity<FakeProductDTO[]> forEntity = restTemplate.getForEntity(URL.concat(PRODUCTS), FakeProductDTO[].class);
        return Arrays.asList(Objects.requireNonNull(forEntity.getBody()));
    }

    public FakeProductDTO deleteProduct(Long id){

        ResponseEntity<FakeProductDTO> response = restTemplate.execute(URL.concat(PRODUCTS).concat("/{id}"), HttpMethod.DELETE,
                restTemplate.acceptHeaderRequestCallback(FakeProductDTO.class),
                restTemplate.responseEntityExtractor(FakeProductDTO.class), id);
        return Objects.requireNonNull(response).getBody();
    }

    public FakeProductDTO updateProduct(Long id, FakeProductDTO product){
        return getProductDTO(product, id);
    }

    public FakeProductDTO patchProduct(Long id, FakeProductDTO product) {
        FakeProductDTO dbObject = this.getProductById(id);
        dbObject.setCategory(product.getCategory()==null? dbObject.getCategory() : product.getCategory());
        dbObject.setTitle(product.getTitle()==null? dbObject.getTitle() : product.getTitle());
        dbObject.setDescription(product.getDescription()==null? dbObject.getDescription() : product.getDescription());
        dbObject.setImage(product.getImage()==null? dbObject.getImage() : product.getImage());
        dbObject.setPrice(product.getPrice() > 0? product.getPrice() : dbObject.getPrice());

        return getProductDTO(dbObject, product.getId());
    }

    private FakeProductDTO getProductDTO(FakeProductDTO product, Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<FakeProductDTO> requestEntity = new HttpEntity<>(product, headers);
        ResponseEntity<FakeProductDTO> response = restTemplate.exchange(URL.concat(PRODUCTS).concat("/{id}"), HttpMethod.PUT,
                requestEntity, FakeProductDTO.class, id);
        return response.getBody();
    }
}
