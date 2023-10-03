package com.mystore.spring.boot.fakestore.controller;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;
import com.mystore.spring.boot.fakestore.exception.NoRecordFoundException;
import com.mystore.spring.boot.fakestore.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db-store/v1/")
public class ProductController {


    //@Qualifier("dbProductService")
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping(value = "product/new")
    public ProductDTO createProduct(@RequestBody ProductDTO product){
        return productService.createProduct(product);
    }

    @GetMapping("product/find/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id) throws NoRecordFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("product/find-all")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("product/find-with")
    public List<ProductDTO> getAllProductsWithLimit(@PageableDefault(size = 10) Pageable pageable){
        return productService.getProductsWithLimit(pageable);
    }

    @GetMapping("product/find-by-category/{id}")
    public List<ProductDTO>  getProductByCategoryId(@PathVariable("id") Long id) throws NoRecordFoundException {
        return productService.getProductsByCategory(id);
    }


    @DeleteMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) throws NoRecordFoundException{
        return productService.deleteProduct(id);
    }

    @PutMapping("product/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO product) throws NoRecordFoundException{
        return productService.updateProduct(product);
    }

    @PatchMapping("product/update-any")
    public ProductDTO patchProduct(@RequestBody ProductDTO product) throws NoRecordFoundException{
        return productService.patchProduct(product);
    }

}
