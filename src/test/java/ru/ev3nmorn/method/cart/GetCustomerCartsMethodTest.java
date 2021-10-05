package ru.ev3nmorn.method.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.builder.CartBuilder;
import ru.ev3nmorn.dto.mapper.CartMapper;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.impl.GetCustomerCartsMethodImpl;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetCustomerCartsMethodTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private CartMapper cartMapper;

    private GetCustomerCartsMethod getCustomerCartsMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        getCustomerCartsMethod = new GetCustomerCartsMethodImpl(cartRepository, cartProductRepository, cartMapper);
    }

    @Test
    public void customerWithoutCartsTest() {
        Exception exception = assertThrows(ApiException.class,
                () -> getCustomerCartsMethod.processToResponse(ID));

        assertEquals("Customer doesn't have any carts", exception.getMessage());
    }

    @Test
    public void customerCartsMethod() {
        when(cartRepository.findCartsByCustomerId(ID))
                .thenReturn(List.of(new CartBuilder()
                        .defaultCart()
                        .build()));

        ResponseEntity<?> response = getCustomerCartsMethod.processToResponse(ID);

        verify(cartRepository, times(1))
                .findCartsByCustomerId(ID);
        verify(cartProductRepository, times(1))
                .findByCartId(anyInt());
        verify(cartMapper, times(1))
                .toDTO(anyMap());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
