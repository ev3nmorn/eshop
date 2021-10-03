package ru.ev3nmorn.service;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ResponseEntity<List<ProductDTO>> getAllProducts();
    ResponseEntity<ProductDTO> getProductById(Integer id);
    void addProduct(ProductDTO product);
    void editProduct(ProductDTO product, Integer id);
    void deleteProduct(Integer id);

}
