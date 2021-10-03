package ru.ev3nmorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> allProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> productById(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO product) {
        productService.addProduct(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDTO product,
                                         @PathVariable("id") Integer id) {
        productService.editProduct(product, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
