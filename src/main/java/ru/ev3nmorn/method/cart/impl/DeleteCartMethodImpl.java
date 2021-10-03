package ru.ev3nmorn.method.cart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.DeleteCartMethod;
import ru.ev3nmorn.repository.CartRepository;

@Component
public class DeleteCartMethodImpl implements DeleteCartMethod {

    private final CartRepository cartRepository;

    @Autowired
    public DeleteCartMethodImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void process(Integer id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
        } else {
            throw new ApiException("Cart not found", HttpStatus.NOT_FOUND, "DeleteCartMethod");
        }
    }
}
