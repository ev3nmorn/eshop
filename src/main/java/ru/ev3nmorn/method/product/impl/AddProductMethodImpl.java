package ru.ev3nmorn.method.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.dto.mapper.ProductMapper;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.AddProductMethod;
import ru.ev3nmorn.repository.ProductRepository;

@Component
public class AddProductMethodImpl implements AddProductMethod {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public AddProductMethodImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void process(ProductDTO product) {
        if (existsName(product.getName())) {
            throw new ApiException("Product with the same name is already exist", HttpStatus.BAD_REQUEST, "AddProductMethod");
        }

        productRepository.save(productMapper.fromDTO(product));
    }

    private boolean existsName(String name) {
        Product product = productRepository.findByName(name);

        return product != null;
    }
}
