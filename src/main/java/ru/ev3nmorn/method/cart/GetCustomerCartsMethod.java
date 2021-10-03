package ru.ev3nmorn.method.cart;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.CartDTO;

import java.util.List;

public interface GetCustomerCartsMethod {

    ResponseEntity<List<CartDTO>> processToResponse(Integer id);

}
