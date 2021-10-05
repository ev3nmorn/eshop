package ru.ev3nmorn.method.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ev3nmorn.builder.CartBuilder;
import ru.ev3nmorn.dto.CartDTO;
import ru.ev3nmorn.dto.mapper.CartMapper;
import ru.ev3nmorn.exception.ApiException;
import ru.ev3nmorn.method.cart.impl.GetCartByIdMethodImpl;
import ru.ev3nmorn.repository.CartProductRepository;
import ru.ev3nmorn.repository.CartRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetCartByIdMethodTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartProductRepository cartProductRepository;
    @Mock
    private CartMapper cartMapper;

    private GetCartByIdMethod getCartByIdMethod;

    private static final Integer ID = 1;

    @BeforeEach
    public void initMethod() {
        getCartByIdMethod = new GetCartByIdMethodImpl(cartProductRepository, cartRepository, cartMapper);
    }

    @Test
    public void cartNotFoundTest() {
        Exception exception = assertThrows(ApiException.class,
                () -> getCartByIdMethod.processToResponse(ID));

        assertEquals("Cart not found", exception.getMessage());
    }

    @Test
    public void cartByIdTest() {
        when(cartRepository.findById(ID))
                .thenReturn(Optional.of(new CartBuilder()
                        .defaultCart()
                        .withId(ID)
                        .build()));
        when(cartMapper.toDTO(anyMap()))
                .thenReturn(List.of(new CartDTO()));

        ResponseEntity<?> response = getCartByIdMethod.processToResponse(ID);

        verify(cartRepository, times(1))
                .findById(ID);
        verify(cartProductRepository, times(1))
                .findByCartId(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
