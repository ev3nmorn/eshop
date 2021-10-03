package ru.ev3nmorn.method.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.DeleteProductMethod;
import ru.ev3nmorn.repository.ProductRepository;

@Component
public class DeleteProductMethodImpl implements DeleteProductMethod {

    private final ProductRepository productRepository;

    @Autowired
    public DeleteProductMethodImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void process(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ApiException("Product not found", HttpStatus.NOT_FOUND, "DeleteProductMethod");
        }
    }
}
