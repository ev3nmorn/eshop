package ru.ev3nmorn.method.cart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.dto.CartDTO;
import ru.ev3nmorn.dto.mapper.CartMapper;
import ru.ev3nmorn.model.CartProduct;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.GetCustomerCartsMethod;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetCustomerCartsMethodImpl implements GetCustomerCartsMethod {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final CartMapper cartMapper;

    @Autowired
    public GetCustomerCartsMethodImpl(CartRepository cartRepository, CartProductRepository cartProductRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartMapper = cartMapper;
    }

    public ResponseEntity<List<CartDTO>> processToResponse(Integer id) {
        List<Cart> carts = cartRepository.findCartsByCustomerId(id);
        Map<Cart, List<CartProduct>> cartProductsMap = new HashMap<>();

        if (carts.isEmpty()) {
            throw new ApiException("Customer doesn't have any carts", HttpStatus.NOT_FOUND, "GetCustomerCartsMethod");
        }

        for (Cart cart : carts) {
            cartProductsMap.put(cart, cartProductRepository.findByCartId(cart.getId()));
        }

        return new ResponseEntity<>(cartMapper.toDTO(cartProductsMap), HttpStatus.OK);
    }
}
