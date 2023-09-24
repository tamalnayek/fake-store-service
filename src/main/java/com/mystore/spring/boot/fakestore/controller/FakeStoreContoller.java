package com.mystore.spring.boot.fakestore.controller;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;
import com.mystore.spring.boot.fakestore.service.FakeStoreSevice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/store/")
public class FakeStoreContoller {

    private final FakeStoreSevice service;

    public FakeStoreContoller(FakeStoreSevice service){
        this.service=service;
    }
    @PostMapping(value = "create")
    public ProductDTO createProduct(@RequestBody ProductDTO product){
        return service.createProduct(product);
    }

    @GetMapping("search/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id){
        return service.getProductById(id);
    }

    @GetMapping("search")
    public List<ProductDTO> getAllProducts(){
        return service.getAllProducts();
    }

    @DeleteMapping("delete/{id}")
    public ProductDTO deleteProduct(@PathVariable("id") Long id){
        return service.deleteProduct(id);
    }

    @PutMapping("update")
    public ProductDTO updateProduct(@RequestBody ProductDTO product){
        return service.updateProduct(product);
    }

    @PatchMapping("update-any")
    public ProductDTO patchProduct(@RequestBody ProductDTO product){
        return service.patchProduct(product);
    }

}
