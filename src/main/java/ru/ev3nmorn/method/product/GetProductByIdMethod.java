package ru.ev3nmorn.method.product;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.ProductDTO;

public interface GetProductByIdMethod {

    ResponseEntity<ProductDTO> processToResponse(Integer id);

}
