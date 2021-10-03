package ru.ev3nmorn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ev3nmorn.dto.ProductDTO;
import ru.ev3nmorn.method.product.*;
import ru.ev3nmorn.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final GetAllProductsMethod getAllProductsMethod;
    private final GetProductByIdMethod getProductByIdMethod;
    private final AddProductMethod addProductMethod;
    private final EditProductMethod editProductMethod;
    private final DeleteProductMethod deleteProductMethod;

    @Autowired
    public ProductServiceImpl(GetAllProductsMethod getAllProductsMethod, GetProductByIdMethod getProductByIdMethod,
                              AddProductMethod addProductMethod, EditProductMethod editProductMethod,
                              DeleteProductMethod deleteProductMethod) {
        this.getAllProductsMethod = getAllProductsMethod;
        this.getProductByIdMethod = getProductByIdMethod;
        this.addProductMethod = addProductMethod;
        this.editProductMethod = editProductMethod;
        this.deleteProductMethod = deleteProductMethod;
    }

    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return getAllProductsMethod.processToResponse();
    }

    public ResponseEntity<ProductDTO> getProductById(Integer id) {
        return getProductByIdMethod.processToResponse(id);
    }

    public void addProduct(ProductDTO product) {
        addProductMethod.process(product);
    }

    public void editProduct(ProductDTO product, Integer id) {
        editProductMethod.process(product, id);
    }

    public void deleteProduct(Integer id) {
        deleteProductMethod.process(id);
    }
}
