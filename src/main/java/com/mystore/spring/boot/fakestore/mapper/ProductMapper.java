package com.mystore.spring.boot.fakestore.mapper;

import com.mystore.spring.boot.fakestore.dto.ProductDTO;
import com.mystore.spring.boot.fakestore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    public static final ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
    ProductDTO mapToProductDTO(Product product);
    Product mapToProduct(ProductDTO productDTO);

    default Product mapToPatchUpdateProduct(Product source, Product target){
        target.setCategory(source.getCategory()!=null? source.getCategory() : target.getCategory());
        target.setTitle(source.getTitle()!=null? source.getTitle() : target.getTitle());
        target.setDescription(source.getDescription()!=null? source.getDescription() : target.getDescription());
        target.setImage(source.getImage()!=null? source.getImage() : target.getImage());
        target.setPrice(source.getPrice() > 0? target.getPrice() : source.getPrice());
        return target;
    }
}
