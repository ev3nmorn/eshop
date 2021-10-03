package ru.ev3nmorn.service;

import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.dto.CartDTO;

import java.util.List;

public interface CartService {

    ResponseEntity<List<CartDTO>> allCarts();
    ResponseEntity<CartDTO> getCartById(Integer id);
    ResponseEntity<List<CartDTO>> getCustomerCarts(Integer id);
    void addCart(Integer customerId);
    void deleteCart(Integer id);
    void addProductToCart(Integer id, Integer productId);
    void deleteProductFromCart(Integer cartId, Integer productId);

}
