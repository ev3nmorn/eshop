package ru.ev3nmorn.method.cart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.model.CartProduct;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.AddProductToCartMethod;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;
import ru.ev3nmorn.repository.ProductRepository;

@Component
public class AddProductToCartMethodImpl implements AddProductToCartMethod {

    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;

    @Autowired
    public AddProductToCartMethodImpl(ProductRepository productRepository,
                                      CartProductRepository cartProductRepository,
                                      CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartRepository = cartRepository;
    }

    public void process(Integer id, Integer productId) {
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ApiException("Cart not found", HttpStatus.NOT_FOUND, "AddProductToCartMethod"));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ApiException("Product not found", HttpStatus.NOT_FOUND, "AddProductToCartMethod"));

        cartProductRepository.save(new CartProduct(product, cart));
    }
}
