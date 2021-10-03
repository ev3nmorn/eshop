package ru.ev3nmorn.method.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.dto.mapper.ProductMapper;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.GetProductByIdMethod;
import ru.ev3nmorn.repository.ProductRepository;

@Component
public class GetProductByIdMethodImpl implements GetProductByIdMethod {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public GetProductByIdMethodImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ResponseEntity<ProductDTO> processToResponse(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found", HttpStatus.NOT_FOUND, "GetProductByIdMethod"));

        return new ResponseEntity<>(productMapper.toDTO(product), HttpStatus.OK);
    }
}
