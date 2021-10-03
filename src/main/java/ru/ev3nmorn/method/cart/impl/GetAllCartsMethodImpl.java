package ru.ev3nmorn.method.cart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.dto.CartDTO;
import ru.ev3nmorn.dto.mapper.CartMapper;
import ru.ev3nmorn.model.CartProduct;
import ru.ev3nmorn.method.cart.GetAllCartsMethod;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetAllCartsMethodImpl implements GetAllCartsMethod {

    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Autowired
    public GetAllCartsMethodImpl(CartProductRepository cartProductRepository, CartRepository cartRepository, CartMapper cartMapper) {
        this.cartProductRepository = cartProductRepository;
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public ResponseEntity<List<CartDTO>> processToResponse() {
        Iterable<Cart> carts = cartRepository.findAll();
        Map<Cart, List<CartProduct>> cartProductsMap = new HashMap<>();

        for (Cart cart : carts) {
            cartProductsMap.put(cart, cartProductRepository.findByCartId(cart.getId()));
        }

        return new ResponseEntity<>(cartMapper.toDTO(cartProductsMap), HttpStatus.OK);
    }
}
