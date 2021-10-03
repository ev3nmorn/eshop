package ru.ev3nmorn.method.product;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.ProductDTO;

import java.util.List;

public interface GetAllProductsMethod {

    ResponseEntity<List<ProductDTO>> processToResponse();

}
