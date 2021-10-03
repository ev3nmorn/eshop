package ru.ev3nmorn.method.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.dto.mapper.ProductMapper;
import ru.ev3nmorn.method.product.GetAllProductsMethod;
import ru.ev3nmorn.repository.ProductRepository;

import java.util.List;

@Component
public class GetAllProductsMethodImpl implements GetAllProductsMethod {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public GetAllProductsMethodImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ResponseEntity<List<ProductDTO>> processToResponse() {
        return new ResponseEntity<>(productMapper.toDTO(productRepository.findAll()), HttpStatus.OK);
    }
}
