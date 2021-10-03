package ru.ev3nmorn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ev3nmorn.dto.CartDTO;
import ru.ev3nmorn.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> allCarts() {
        return cartService.allCarts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCart(@PathVariable("id") Integer id) {
        return cartService.getCartById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Integer id) {
        cartService.deleteCart(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{cartId}/product/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable("cartId") Integer cartId,
                                              @PathVariable("productId") Integer productId) {
        cartService.addProductToCart(cartId, productId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable("cartId") Integer cartId,
                                              @PathVariable("productId") Integer productId) {
        cartService.deleteProductFromCart(cartId, productId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
