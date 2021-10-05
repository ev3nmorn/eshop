package ru.ev3nmorn.method.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.CartBuilder;
import ru.ev3nmorn.builder.ProductBuilder;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.impl.AddProductToCartMethodImpl;
import ru.ev3nmorn.model.Cart;
import ru.ev3nmorn.model.Product;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;
import ru.ev3nmorn.repository.ProductRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AddProductToCartMethodTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private CartRepository cartRepository;

    private AddProductToCartMethod addProductToCartMethod;

    private final static Integer CART_ID = 1;
    private final static Integer PRODUCT_ID = 1;

    @BeforeEach
    public void initMethod() {
        addProductToCartMethod = new AddProductToCartMethodImpl(productRepository,
                cartProductRepository, cartRepository);
    }

    @Test
    public void cartNotFoundTest() {
        Exception exception = assertThrows(ApiException.class,
                () -> addProductToCartMethod.process(CART_ID, PRODUCT_ID));

        assertEquals("Cart not found", exception.getMessage());
    }

    @Test
    public void productNotFoundTest() {
        Cart cart = new CartBuilder()
                .defaultCart()
                .withId(CART_ID)
                .build();

        when(cartRepository.findById(CART_ID))
                .thenReturn(Optional.of(cart));

        Exception exception = assertThrows(ApiException.class,
                () -> addProductToCartMethod.process(CART_ID, PRODUCT_ID));

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    public void addProductToCartTest() {
        Cart cart = new CartBuilder()
                .defaultCart()
                .withId(CART_ID)
                .build();

        Product product = new ProductBuilder()
                .defaultProduct()
                .withId(PRODUCT_ID)
                .build();

        when(cartRepository.findById(CART_ID))
                .thenReturn(Optional.of(cart));
        when(productRepository.findById(PRODUCT_ID))
                .thenReturn(Optional.of(product));

        addProductToCartMethod.process(CART_ID, PRODUCT_ID);

        verify(cartProductRepository, times(1))
                .save(any());
    }
}
