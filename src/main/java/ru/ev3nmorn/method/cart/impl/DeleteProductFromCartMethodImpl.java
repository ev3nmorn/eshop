package ru.ev3nmorn.method.cart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.ev3nmorn.model.CartProduct;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.DeleteProductFromCartMethod;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;
import ru.ev3nmorn.repository.ProductRepository;

import java.util.List;

@Component
public class DeleteProductFromCartMethodImpl implements DeleteProductFromCartMethod {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    @Autowired
    public DeleteProductFromCartMethodImpl(CartRepository cartRepository, ProductRepository productRepository,
                                           CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
    }

    public void process(Integer cartId, Integer productId) {
        if (!cartRepository.existsById(cartId)) {
            throw new ApiException("Cart not found", HttpStatus.NOT_FOUND, "AddProductToCartMethod");
        }
        if (!productRepository.existsById(productId)) {
            throw new ApiException("Product not found", HttpStatus.NOT_FOUND, "AddProductToCartMethod");
        }

        List<CartProduct> cartProducts = cartProductRepository.findByCartIdAndProductId(cartId, productId);
        if (cartProducts.isEmpty()) {
            throw new ApiException("Cart doesn't contain this product", HttpStatus.NOT_FOUND, "DeleteProductFromCartMethod");
        }

        cartProductRepository.deleteAll(cartProducts);
    }
}
