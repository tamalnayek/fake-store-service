package com.mystore.spring.boot.fakestore.controller;

import com.mystore.spring.boot.fakestore.dto.FakeProductDTO;
import com.mystore.spring.boot.fakestore.service.FakeStoreSevice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fake-store/v1/")
public class FakeStoreContoller {

    private final FakeStoreSevice service;

    public FakeStoreContoller(FakeStoreSevice service){
        this.service=service;
    }
    @PostMapping(value = "create")
    public FakeProductDTO createProduct(@RequestBody FakeProductDTO product){
        return service.createProduct(product);
    }

    @GetMapping("search/{id}")
    public FakeProductDTO getProductById(@PathVariable("id") Long id){
        return service.getProductById(id);
    }

    @GetMapping("search")
    public List<FakeProductDTO> getAllProducts(){
        return service.getAllProducts();
    }

    @DeleteMapping("delete/{id}")
    public FakeProductDTO deleteProduct(@PathVariable("id") Long id){
        return service.deleteProduct(id);
    }

    @PutMapping("update/{id}")
    public FakeProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody FakeProductDTO product){
        return service.updateProduct(id, product);
    }

    @PatchMapping("update-any/{id}")
    public FakeProductDTO patchProduct(@PathVariable("id") Long id, @RequestBody FakeProductDTO product){
        return service.patchProduct(id, product);
    }

}
