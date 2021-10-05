package ru.ev3nmorn.method.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ev3nmorn.builder.CartBuilder;
import ru.ev3nmorn.builder.CartProductBuilder;
import ru.ev3nmorn.builder.ProductBuilder;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.impl.DeleteProductFromCartMethodImpl;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;
import ru.ev3nmorn.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProductFromCartMethodTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartProductRepository cartProductRepository;

    private DeleteProductFromCartMethod deleteProductFromCartMethod;

    private static final Integer CART_ID = 1;
    private static final Integer PRODUCT_ID = 1;

    @BeforeEach
    public void initMethod() {
        deleteProductFromCartMethod = new DeleteProductFromCartMethodImpl(cartRepository, productRepository,
                cartProductRepository);
    }

    @Test
    public void cartNotFoundTest() {
        Exception exception = assertThrows(ApiException.class,
                () -> deleteProductFromCartMethod.process(CART_ID, PRODUCT_ID));

        assertEquals("Cart not found", exception.getMessage());
    }

    @Test
    public void productNotFoundTest() {
        when(cartRepository.existsById(CART_ID))
                .thenReturn(true);

        Exception exception = assertThrows(ApiException.class,
                () -> deleteProductFromCartMethod.process(CART_ID, PRODUCT_ID));

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    public void cartWithoutProductTest() {
        when(cartRepository.existsById(CART_ID))
                .thenReturn(true);
        when(productRepository.existsById(PRODUCT_ID))
                .thenReturn(true);
        when(cartProductRepository.findByCartIdAndProductId(CART_ID, PRODUCT_ID))
                .thenReturn(new ArrayList<>());

        Exception exception = assertThrows(ApiException.class,
                () -> deleteProductFromCartMethod.process(CART_ID, PRODUCT_ID));

        assertEquals("Cart doesn't contain this product", exception.getMessage());
    }

    @Test
    public void deleteProductFromCartTest() {
        when(cartRepository.existsById(CART_ID))
                .thenReturn(true);
        when(productRepository.existsById(PRODUCT_ID))
                .thenReturn(true);
        when(cartProductRepository.findByCartIdAndProductId(CART_ID, PRODUCT_ID))
                .thenReturn(List.of(new CartProductBuilder()
                        .defaultCartProduct()
                        .withCart(new CartBuilder()
                                .defaultCart()
                                .withId(CART_ID)
                                .build())
                        .withProduct(new ProductBuilder()
                                .defaultProduct()
                                .withId(PRODUCT_ID)
                                .build())
                        .build()));

        deleteProductFromCartMethod.process(CART_ID, PRODUCT_ID);

        verify(cartProductRepository, times(1))
                .deleteAll(anyIterable());
    }
}
