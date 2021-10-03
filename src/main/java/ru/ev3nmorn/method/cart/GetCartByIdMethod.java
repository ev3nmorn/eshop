package ru.ev3nmorn.method.cart;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.CartDTO;

public interface GetCartByIdMethod {

    ResponseEntity<CartDTO> processToResponse(Integer id);

}
