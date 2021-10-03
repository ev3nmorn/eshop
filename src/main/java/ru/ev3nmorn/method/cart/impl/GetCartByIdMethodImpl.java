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
import ru.ev3nmorn.method.cart.GetCartByIdMethod;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetCartByIdMethodImpl implements GetCartByIdMethod {

    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Autowired
    public GetCartByIdMethodImpl(CartProductRepository cartProductRepository, CartRepository cartRepository, CartMapper cartMapper) {
        this.cartProductRepository = cartProductRepository;
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public ResponseEntity<CartDTO> processToResponse(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ApiException("Cart not found", HttpStatus.NOT_FOUND, "GetCartByIdMethod"));
        Map<Cart, List<CartProduct>> cartProductsMap = new HashMap<>();

        cartProductsMap.put(cart, cartProductRepository.findByCartId(cart.getId()));

        return new ResponseEntity<>(cartMapper.toDTO(cartProductsMap).stream().findFirst().get(), HttpStatus.OK);
    }

}
