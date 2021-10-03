package ru.ev3nmorn.method.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.product.EditProductMethod;
import ru.ev3nmorn.repository.ProductRepository;

@Component
public class EditProductMethodImpl implements EditProductMethod {

    private final ProductRepository productRepository;

    @Autowired
    public EditProductMethodImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void process(ProductDTO product, Integer id) {
        if (id == null) {
            throw new ApiException("Id is mandatory to update product info", HttpStatus.BAD_REQUEST, "EditProductMethod");
        }

        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found", HttpStatus.NOT_FOUND, "EditProductMethod"));

        if (existsName(product.getName(), id)) {
            throw new ApiException("Product with the same name is already exist", HttpStatus.BAD_REQUEST, "EditProductMethod");
        }

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());

        productRepository.save(productToUpdate);
    }

    private boolean existsName(String name, Integer id) {
        Product product = productRepository.findByName(name);

        return product != null && !product.getId().equals(id);
    }
}
